package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.*;

import java.util.*;

public class Rules implements IRules{
    private boolean isEnemy = false;
    private List<String[]> enemyList = new ArrayList<>();
    private boolean isFriend = false;
    private List<String[]> friendList = new ArrayList<>();

    @Override
    public void addMustBeTogether(String a, String b) {
        String[] arr = new String[]{a, b};
        this.friendList.add(arr);
        this.isFriend = true;
    }

    @Override
    public void addMustBeApart(String a, String b) {
        String[] arr = new String[]{a, b};
        this.enemyList.add(arr);
        this.isEnemy = true;
    }

    @Override
    public boolean isPlanOK(IPlan p) {
        boolean result = true;
        if(isFriend) {
            if(!checkFriend(p)) return false;
        }
        if(isEnemy) {
            if(!checkEnemy(p)) return false;
        }
        return result;
    }

    private boolean checkFriend(IPlan p) {
        boolean result;
        for(int i = 0; i < p.getNumberOfTables(); i++) {
            result = true;
            Set<String> guestSet = p.getGuestsAtTable(i);
            for(String[] subFriend : this.friendList) {
                if(guestSet.contains(subFriend[0])) {
                    if(!guestSet.contains(subFriend[1])) result = false;
                }
                if(guestSet.contains(subFriend[1])) {
                    if(!guestSet.contains(subFriend[0])) result = false;
                }
                if(!result && isTableFull(p, i)) return false;
            }
        }
        return true;
    }

    private boolean checkEnemy(IPlan p) {
        for(int i = 0; i < p.getNumberOfTables(); i++) {
            Set<String> guests = p.getGuestsAtTable(i);
            for(String[] subEnemy : this.enemyList) {
                if(guests.containsAll(Arrays.asList(subEnemy))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isTableFull(IPlan p, int t) {
        if(p.getGuestsAtTable(t).size() == p.getSeatsPerTable()) {
            return true;
        }
        return false;
    }

}
