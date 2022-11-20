package uk.ac.aber.cs21120.wedding.solution;

import uk.ac.aber.cs21120.wedding.interfaces.*;

import java.util.*;

public class Rules implements IRules{
    private boolean isEnemy = false;
    private List<String[]> enemyList = new ArrayList<>();
    private HashMap<String[], Boolean> friendMap = new HashMap<>();
    private boolean isFriend = false;
    private List<String[]> friendList = new ArrayList<>();

    @Override
    public void addMustBeTogether(String a, String b) {
        String[] arr = new String[]{a, b};
        this.friendList.add(arr);
        this.isFriend = true;
        friendMap.put(arr, false);
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
        HashMap<String[], Boolean> tempMap = new HashMap<>(this.friendMap);
        for(int i = 0; i < p.getNumberOfTables(); i++) {

            if(!p.isTableFull(i)) return true;

            Set<String> guests = p.getGuestsAtTable(i);
            for(String[] subFriend : this.friendList) {
                if(guests.containsAll(Arrays.asList(subFriend))) {
                    tempMap.replace(subFriend, true);
                }
            }
        }
        boolean allTrue = tempMap.values().stream().allMatch(x -> x);
        return allTrue;
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
}
