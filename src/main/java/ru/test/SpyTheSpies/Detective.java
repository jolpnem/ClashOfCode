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

        return commands.toArray(new String[20]);
    }

    private boolean allInnocentsHasNotAttribute(String attr) {
        return attr.equals("french");
    }

    private boolean allSpiesHasAttribute(String attr) {
        for (String spyDesc : getSpiesDescriptions())
            if (!spyDesc.contains(attr))
                return false;

        return true;
    }

    private String[] getSpiesDescriptions() {
        String[] res = new String[spiesNames.length];

        int c = 0;
        for (String suspectsDescription: suspectsDescriptions)
            for (String spiesName: spiesNames)
                if (suspectsDescription.contains(spiesName)) res[c++] = suspectsDescription;

        return res;
    }

    private String[] getSpiesAttributes() {
        LinkedHashSet<String> res = new LinkedHashSet<>();

        for (String spyName : spiesNames) {
            for (String suspectDesc: suspectsDescriptions)
                if (suspectDesc.contains(spyName))
                    res.addAll(Arrays.asList(SuspectDescriptionParser.getAttributes(suspectDesc)));
        }

        return res.toArray(new String[]{});
    }
}
