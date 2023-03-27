package c1020g1.social_network.model;

import java.util.List;

public class SuggestFriend {
    private User suggestFriend;
    private List<User> mutualFriends;

    public List<User> getMutualFriends() {
        return mutualFriends;
    }

    public void setMutualFriends(List<User> mutualFriends) {
        this.mutualFriends = mutualFriends;
    }

    public User getSuggestFriend() {
        return suggestFriend;
    }

    public void setSuggestFriend(User suggestFriend) {
        this.suggestFriend = suggestFriend;
    }
}
