package c1020g1.social_network.controller;

import c1020g1.social_network.model.*;

import c1020g1.social_network.service.GroupRequestService;
import c1020g1.social_network.service.GroupService;
import c1020g1.social_network.service.GroupUserService;
import c1020g1.social_network.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import c1020g1.social_network.model.GroupUser;
import c1020g1.social_network.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class GroupController {
    @Autowired
    private GroupRequestService groupRequestService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupUserService groupUserService;
    @Autowired
    private PostService postService;  // getAllGroupMember():Observable<any>{
    //   return this.http.get((this.API + '/member'))
    // }

    //show list group
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ResponseEntity<List<GroupSocial>> listAllGroup() {
        List<GroupSocial> Groups = groupService.findAll();
        if (Groups.isEmpty()) {
            return new ResponseEntity<List<GroupSocial>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<GroupSocial>>(Groups, HttpStatus.OK);
        }
    }

    //tim kiem group theo ten
    @RequestMapping(value = "/group-search/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<GroupSocial>> listGroupByName(@PathVariable String name) {
        List<GroupSocial> Groups = groupService.findGroupByNameContaining(name);
        if (Groups.isEmpty()) {
            return new ResponseEntity<List<GroupSocial>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<GroupSocial>>(Groups, HttpStatus.OK);
        }
    }

    //hien group detail
    @RequestMapping(value = "/group-detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupSocial> getGroup(@PathVariable("id") Integer id) {
        System.out.println("Fetching Group with id " + id);
        GroupSocial Group = groupService.findById(id);
        if (Group == null) {
            System.out.println("Group with id " + id + " not found");
            return new ResponseEntity<GroupSocial>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<GroupSocial>(Group, HttpStatus.OK);
    }

    //Xoa group
    @RequestMapping(value = "/group-delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGroup(@PathVariable("id") Integer id) {
        System.out.println("Fetching & Deleting Group with id " + id);

        GroupSocial Group = groupService.findById(id);
        if (Group == null) {
            System.out.println("Unable to delete. Group with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        groupService.remove(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //list Member
    @RequestMapping(value = "/group-member/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<GroupUser>> listAllGroupMember(@PathVariable Integer id) {
        List<GroupUser> groupUsers = groupUserService.findAllGroupMember(id);
        if (groupUsers.isEmpty()) {
            return new ResponseEntity<List<GroupUser>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<GroupUser>>(groupUsers, HttpStatus.OK);
        }
    }

    //Display page feed
    @RequestMapping(value = "/group-list-post/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> listAllPostGroup(@PathVariable Integer id) {
        List<Post> posts = postService.findAllPostGroup(id);
        if (posts.isEmpty()) {
            return new ResponseEntity<List<Post>>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
        }
    }

    //edit Group
    @RequestMapping(value = "/group-edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<GroupSocial> updateGroup(@PathVariable("id") Integer id, @RequestBody GroupSocial Group) {
        System.out.println("Updating Group " + id);

        GroupSocial currentGroup = groupService.findById(id);

        if (currentGroup == null) {
            System.out.println("Customer with id " + id + " not found");
            return new ResponseEntity<GroupSocial>(HttpStatus.NOT_FOUND);
        }

        currentGroup.setImageAvatarUrl(Group.getImageAvatarUrl());
        currentGroup.setImageBackground(Group.getImageBackground());
        currentGroup.setScope(Group.getScope());

        groupService.save(currentGroup);
        return new ResponseEntity<GroupSocial>(currentGroup, HttpStatus.OK);
    }

    //Gui request join group
    @RequestMapping(value = "/group-request", method = RequestMethod.POST)
    public ResponseEntity<GroupRequest> requestGroup(@RequestBody GroupRequest groupRequest) {
        if (groupRequestService.addGroupRequest(groupRequest).equals("NG"))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private WarningService warningService;

    @GetMapping("/request/list/group/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByGroup(@PathVariable int id,
                                                                    @RequestParam(required = false) String key,
                                                                    Pageable pageable) {
        if (key == null) {
            key = "";
        }
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByGroupAndKey(id, key, pageable), HttpStatus.OK);
    }

    @GetMapping("/request/list/user/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByUser(@PathVariable int id, Pageable pageable) {
        User user = userService.getUserById(id);
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByUser(user, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/request/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int id) {
        if (groupRequestService.findById(id) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        groupRequestService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PostMapping("/request/save")
    public ResponseEntity<Void> saveRequest(@RequestBody GroupRequest groupRequest) {
        if (groupRequest.getGroup() == null || groupRequest.getUser() == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        if (groupService.findById(groupRequest.getGroup().getGroupId()) == null
                || userService.getUserById(groupRequest.getUser().getUserId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        GroupUser groupUser = groupUserService.findExist(groupRequest.getGroup().getGroupId(), groupRequest.getUser().getUserId());
        if (groupRequestService.findExist(groupRequest) != null || groupUser != null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        groupRequestService.save(groupRequest);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @PostMapping("/request/accept/{id}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int id) {
        GroupRequest groupRequest = groupRequestService.findById(id);
        if (groupRequest == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        GroupUser groupUser = new GroupUser();
        groupUser.setGroup(groupRequest.getGroup());
        groupUser.setUser(groupRequest.getUser());
        groupUserService.save(groupUser);
        groupRequestService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/member/list/{id}")
    public ResponseEntity<Page<GroupUser>> memberList(@RequestParam(required = false) String key, @PathVariable int id,
                                                      @PageableDefault(size = 11) Pageable pageable) {
        if (key == null) {
            key = "";
        }
        Page<GroupUser> all = groupUserService.findAllByGroupAndUsernameContainingOrderByUsername(id, key, pageable);
        return new ResponseEntity<Page<GroupUser>>(all, HttpStatus.OK);
    }

    @PostMapping("/member/warning")
    public ResponseEntity<Void> warning(@RequestBody GroupWarning groupWarning) {
        if (groupUserService.findById(groupWarning.getGroupUser().getGroupUserId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        warningService.save(groupWarning);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<GroupUser> member(@PathVariable int id) {
        GroupUser groupUser = groupUserService.findById(id);
        if (groupUser == null) {
            return new ResponseEntity<GroupUser>(groupUser, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<GroupUser>(groupUser, HttpStatus.OK);
    }

    @GetMapping("/member/warning/{id}")
    public ResponseEntity<Page<GroupWarning>> warningList(@PathVariable int id, @PageableDefault(size = 5) Pageable pageable) {
        if (groupUserService.findById(id) == null) {
            return new ResponseEntity<Page<GroupWarning>>(Page.empty(), HttpStatus.NO_CONTENT);
        }
        GroupUser groupUser = groupUserService.findById(id);
        return new ResponseEntity<Page<GroupWarning>>(warningService.findAllByGroupUserOrderByWarningDateDesc(groupUser, pageable), HttpStatus.OK);
    }

    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (groupUserService.findById(id) != null) {
            warningService.deleteByGroupUserId(id);
            groupUserService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/request/invite/list/{id}")
    public ResponseEntity<List<User>> friendsOfFriendsInviteList(@PathVariable int id) {
        return new ResponseEntity<List<User>>(userService.inviteFriendsOfFriendsList(id), HttpStatus.OK);
    }

    @GetMapping("/request/invite/friends/{id}")
    public ResponseEntity<List<User>> friendsInviteList(@PathVariable int id, @RequestParam int userId) {
        List<User> list = userService.inviteFriendList(id, userId);
        for (User user : list) {
            System.out.println(user.getUserName());
        }
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
}
