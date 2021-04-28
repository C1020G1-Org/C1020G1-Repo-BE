package c1020g1.social_network.controller;

import c1020g1.social_network.model.FriendRequest;
import c1020g1.social_network.model.Friends;
import c1020g1.social_network.service.friend_request_service.FriendRequestService;
import c1020g1.social_network.service.friends_service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FriendsController {

    @Autowired
    private FriendsService friendsService;

    @Autowired
    FriendRequestService friendRequestService;

    // show list friend
    @RequestMapping(value = "/friend-list/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Friends>> getAllList(@PathVariable Integer id) {
        try {
            List<Friends> friendsList = friendsService.findAllFriendById(id);
            if (friendsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(friendsList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Delete friend
    @RequestMapping(value = "friend-delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Friends> deleteFriends(@PathVariable Integer id) {
        try {
            Friends friends = friendsService.findFriendsById(id);
            if (friends == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            friendsService.deleteFriends(friends);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    //show List Suggest Friend
    @RequestMapping(value = "friend-suggest/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Object>> showListSuggest(@PathVariable Integer id) {
        try {
            List<Object> friendSuggestList = friendsService.getAllSuggestFriend(id);
            if (friendSuggestList == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(friendSuggestList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //Accept Friend Request

    /**
     * Author : TùngNT
     * Add Friend
     */
    @PostMapping("/addfriend")
    public ResponseEntity<Void> addNewFriend(@RequestBody FriendRequest friendRequest) {
        try {
            Friends friends1 = new Friends();
            friends1.setUser(friendRequest.getReceiveUser());
            friends1.setFriend(friendRequest.getSendUser());
            if (friendsService.addNewFriend(friends1).equals("NG")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            Friends friends2 = new Friends();
            friends2.setUser(friendRequest.getSendUser());
            friends2.setFriend(friendRequest.getReceiveUser());
            if (friendsService.addNewFriend(friends2).equals("NG")) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            friendRequestService.deleteFriendRequest(friendRequest.getReceiveUser().getUserId(),friendRequest.getSendUser().getUserId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
