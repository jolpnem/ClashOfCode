package ru.test.SpyTheSpies;

import java.util.*;

class Detective {
    private String[] spiesNames;
    private String[] suspectsDescriptions;
    private Set<String> attributes;

    Detective(String[] spiesNames, String[] suspectsDescriptions) {
        this.spiesNames = spiesNames;
        this.suspectsDescriptions = suspectsDescriptions;
        this.attributes = new LinkedHashSet<>();

        fillAttributes();
    }

    private void fillAttributes() {
        for (String susDesc : suspectsDescriptions)
            attributes.addAll(Arrays.asList(SuspectDescriptionParser.getAttributes(susDesc)));
    }

    String[] detect() {
        List<String> commands = new ArrayList<>();

        for (String attr : attributes)
            if (allSpiesHasAttribute(attr) && allInnocentsHasNotAttribute(attr))
                return new String[]{attr};

        for (String attr : attributes)
            if (!allSpiesHasAttribute(attr) && !allInnocentsHasNotAttribute(attr))
                return new String[]{"NOT " + attr};

        return commands.toArray(new String[20]);
    }

    private boolean allInnocentsHasNotAttribute(String attr) {
        for (String innocentDesc : getInnocentsDescriptions())
            if (innocentDesc.contains(attr))
                return false;

        return true;
    }

    private String[] getInnocentsDescriptions() {
        String[] res = new String[getInnocentsCount()];

        int i = 0;
        for (String innocentDesc : suspectsDescriptions)
            for (String innocentName : getInnocentsNames())
                if (innocentDesc.contains(innocentName))
                    res[i++] = innocentDesc;

        return res;
    }

    private String[] getInnocentsNames() {
        String[] res = new String[getInnocentsCount()];

        int i = 0;
        outerLoop:

        for (String suspectName : getSuspectsNames()) {
            for (String spyName: spiesNames)
                if (suspectName.equals(spyName)) continue outerLoop;

                res[i++] = suspectName;
        }

        return res;
    }

    private String[] getSuspectsNames() {
        String[] strings = new String[getSuspectsCount()];

        for (int i = 0; i < getSuspectsCount(); i++)
            strings[i] = SuspectDescriptionParser.getName(suspectsDescriptions[i]);

        return strings;
    }

    private int getSuspectsCount() {
        return suspectsDescriptions.length;
    }

    private int getInnocentsCount() {
        return suspectsDescriptions.length - spiesNames.length;
    }

    private boolean allSpiesHasAttribute(String attr) {
        for (String spyDesc : getSpiesDescriptions())
            if (!spyDesc.contains(attr))
                return false;

        return true;
    }

    private String[] getSpiesDescriptions() {
        String[] res = new String[getSpiesCount()];

        int i = 0;
        for (String suspectsDescription: suspectsDescriptions)
            for (String spyName: spiesNames)
                if (suspectsDescription.contains(spyName))
                    res[i++] = suspectsDescription;

        return res;
    }

    private int getSpiesCount() {
        return spiesNames.length;
    }

//    private String[] getSpiesAttributes() {
//        LinkedHashSet<String> res = new LinkedHashSet<>();
//
//        for (String spyName : spiesNames) {
//            for (String suspectDesc: suspectsDescriptions)
//                if (suspectDesc.contains(spyName))
//                    res.addAll(Arrays.asList(SuspectDescriptionParser.getAttributes(suspectDesc)));
//        }
//
//        return res.toArray(new String[]{});
//    }
}
