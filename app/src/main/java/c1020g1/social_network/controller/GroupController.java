package c1020g1.social_network.controller;

import c1020g1.social_network.model.*;


import c1020g1.social_network.service.group.GroupRequestService;
import c1020g1.social_network.service.group.GroupService;
import c1020g1.social_network.service.group.GroupUserService;
import c1020g1.social_network.service.post.PostService;
import c1020g1.social_network.service.user.UserService;
import c1020g1.social_network.service.ward.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import c1020g1.social_network.model.GroupUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private PostService postService;
    @Autowired
    private WarningService warningService;

    //hien group detail

    /**
     * Author: CuongNVM
     * Get group detail
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-detail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupSocial> getGroup(@PathVariable("id") Integer id) {
        try {
            GroupSocial Group = groupService.findById(id);
            if (Group == null) {
                return new ResponseEntity<GroupSocial>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<GroupSocial>(Group, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Xoa group

    /**
     * Author: CuongNVM
     * Delete group
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteGroup(@PathVariable("id") Integer id) {
        try {
            GroupSocial Group = groupService.findById(id);
            if (Group == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            groupService.remove(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //list Member

    /**
     * Author: CuongNVM
     * Get list member group
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-member/{id}", method = RequestMethod.GET)
    public ResponseEntity<Page<GroupUser>> listAllGroupMember(@PathVariable Integer id, @PageableDefault(size = 4) Pageable pageable) {
        try {
            Page<GroupUser> groupUsers = groupUserService.findAllGroupMember(id, pageable);
            return new ResponseEntity<>(groupUsers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Display page feed

    /**
     * Author: CuongNVM
     * get all post group
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-list-post/{id}", method = RequestMethod.GET)
    public ResponseEntity<Page<Post>> listAllPostGroup(@PathVariable Integer id, @PageableDefault(value = 3) Pageable pageable) {
        try {
            Page<Post> posts = postService.findAllPostGroup(id,pageable);
            if (posts.isEmpty()) {
                return new ResponseEntity<Page<Post>>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<Page<Post>>(posts, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //edit Group

    /**
     * Author: CuongNVM
     * edit profile group
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/group-edit/{id}", method = RequestMethod.PUT)
    public ResponseEntity<GroupSocial> updateGroup(@PathVariable("id") Integer id, @RequestBody GroupSocial group) {
        try {
            GroupSocial currentGroup = groupService.findById(id);

            if (currentGroup == null) {
                return new ResponseEntity<GroupSocial>(HttpStatus.NOT_FOUND);
            }

            currentGroup.setImageAvatarUrl(group.getImageAvatarUrl());
            currentGroup.setImageBackground(group.getImageBackground());
            currentGroup.setScope(group.getScope());

            groupService.save(currentGroup);
            return new ResponseEntity<GroupSocial>(currentGroup, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/group-detail/{groupId}/{userId}", method = RequestMethod.GET)
    public ResponseEntity<GroupUser> findGroupUserByGroupAndUser(@PathVariable Integer groupId, @PathVariable Integer userId) {
        try {
            if (groupService.findById(groupId) == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (userService.getUserById(userId) == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            if (groupUserService.findExist(groupId, userId) == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<GroupUser>(groupUserService.findExist(groupId, userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ResponseEntity<Page<GroupSocial>> listAllGroup(@PageableDefault(size = 5) Pageable pageable, @RequestParam String key) {
        try {
            Page<GroupSocial> Groups = groupService.findAllByGroupName(key, pageable);
            return new ResponseEntity<>(Groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * @author PhinNL
     * get list request by group id
     */
    @GetMapping("/request/list/group/{id}")
    public ResponseEntity<Page<GroupRequest>> getRequestListByGroup(@PathVariable int id, @RequestParam(required = false) String key,
                                                                    @PageableDefault(size = 11) Pageable pageable) {
        if (groupService.findById(id) == null) {
            return new ResponseEntity<Page<GroupRequest>>(Page.empty(), HttpStatus.NOT_FOUND);
        }
        if (key == null) {
            key = "";
        }
        return new ResponseEntity<Page<GroupRequest>>(groupRequestService.findAllByGroupAndKey(id, key, pageable), HttpStatus.OK);
    }

    /**
     * @author PhinNL
     * get list request by user id
     */
    @GetMapping("/request/list/user/{id}")
    public ResponseEntity<List<GroupRequest>> getRequestListByUser(@PathVariable int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<List<GroupRequest>>(groupRequestService.findAllByUser(user), HttpStatus.OK);
    }

    @GetMapping("/group-detail/{id}")
    public ResponseEntity<GroupSocial> getGroupById(@PathVariable int id) {
        return new ResponseEntity<GroupSocial>(groupService.findById(id), HttpStatus.OK);
    }

    /**
     * @author PhinNL
     * delete request by group request id
     */
    @DeleteMapping("/request/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Integer id) {
        if (groupRequestService.findById(id) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        groupRequestService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    /**
     * @author PhinNL
     * create a request
     */
    @PostMapping("/request/save")
    public ResponseEntity<Void> saveRequest(@RequestBody GroupRequest groupRequest) {

        if (groupRequest.getGroupSocial() == null || groupRequest.getUser() == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        if (groupService.findById(groupRequest.getGroupSocial().getGroupId()) == null
                || userService.getUserById(groupRequest.getUser().getUserId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        GroupUser groupUser = groupUserService.findExist(groupRequest.getGroupSocial().getGroupId(), groupRequest.getUser().getUserId());
        if (groupRequestService.findExist(groupRequest) != null || groupUser != null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        groupRequestService.save(groupRequest);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }


    /**
     * @author PhinNL
     * delete request and create member
     */
    @PostMapping("/request/accept/{id}")
    public ResponseEntity<Void> acceptRequest(@PathVariable int id) {
        GroupRequest groupRequest = groupRequestService.findById(id);
        if (groupRequest == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupSocial(groupRequest.getGroupSocial());
        groupUser.setUser(groupRequest.getUser());
        groupUserService.save(groupUser);
        groupRequestService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * @author PhinNL
     * get member list by group id
     */
    @GetMapping("/member/list/{id}")
    public ResponseEntity<Page<GroupUser>> getMemberList(@RequestParam(required = false) String key, @PathVariable int id,
                                                         @PageableDefault(size = 11) Pageable pageable) {
        if (groupService.findById(id) == null) {
            return new ResponseEntity<Page<GroupUser>>(Page.empty(), HttpStatus.NOT_FOUND);
        }
        if (key == null) {
            key = "";
        }
        Page<GroupUser> all = groupUserService.findAllByGroupAndUsernameContainingOrderByUsername(id, key, pageable);
        return new ResponseEntity<Page<GroupUser>>(all, HttpStatus.OK);
    }

    /**
     * @author PhinNL
     * create warning
     */
    @PostMapping("/member/warning")
    public ResponseEntity<Void> warningMember(@RequestBody GroupWarning groupWarning) {
        if (groupUserService.findById(groupWarning.getGroupUser().getGroupUserId()) == null) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        warningService.save(groupWarning);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * @author PhinNL
     * get member by id
     */
    @GetMapping("/member/{id}")
    public ResponseEntity<GroupUser> getMemberById(@PathVariable int id) {
        GroupUser groupUser = groupUserService.findById(id);
        if (groupUser == null) {
            return new ResponseEntity<GroupUser>(groupUser, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GroupUser>(groupUser, HttpStatus.OK);
    }

    /**
     * @author PhinNL
     * get warning list by group user id
     */
    @GetMapping("/member/warning/{id}")
    public ResponseEntity<Page<GroupWarning>> getWarningList(@PathVariable int id, @PageableDefault(size = 5) Pageable pageable) {
        if (groupUserService.findById(id) == null) {
            return new ResponseEntity<Page<GroupWarning>>(Page.empty(), HttpStatus.NOT_FOUND);
        }
        GroupUser groupUser = groupUserService.findById(id);
        return new ResponseEntity<Page<GroupWarning>>(warningService.findAllByGroupUserOrderByWarningDateDesc(groupUser, pageable), HttpStatus.OK);
    }

    /**
     * @author PhinNL
     * delete member by id
     */
    @DeleteMapping("/member/delete/{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable int id) {
        if (groupUserService.findById(id) != null) {
            warningService.deleteByGroupUserId(id);
            groupUserService.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    /**
     * @author PhinNL
     * get list friends of friends admin not in group user and group request by group id
     */
    @GetMapping("/request/invite/list/{id}")
    public ResponseEntity<List<User>> getFriendsOfFriendsInviteList(@PathVariable int id,@RequestParam int userId) {
        if (groupService.findById(id) == null){
            return new ResponseEntity<List<User>>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<User>>(userService.inviteFriendsOfFriendsList(id,userId), HttpStatus.OK);
    }

    /**
     * @author PhinNL
     * get list friend of admin by group id
     */
    @GetMapping("/request/invite/friends/{id}")
    public ResponseEntity<List<User>> getFriendsInviteList(@PathVariable int id, @RequestParam int userId) {
        if (groupService.findById(id) == null || userService.getUserById(userId) == null) {
            return new ResponseEntity<List<User>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
        List<User> list = userService.inviteFriendList(id, userId);
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }

    @GetMapping("/group-request/{userId}")
    public ResponseEntity<List<GroupRequest>> findGroupRequestByUserId(@PathVariable Integer userId) {
        try {
            List<GroupRequest> list = groupRequestService.findGroupRequestByUserId(userId);
            return new ResponseEntity<List<GroupRequest>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
