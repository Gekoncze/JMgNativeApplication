package cz.mg.nativeapplication.explorer.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Value;


public @Utility class SearchResult {
    private final @Mandatory @Link Node result;
    private final @Value int index;

    public SearchResult(@Mandatory Node result, int index) {
        this.result = result;
        this.index = index;
    }

    public @Mandatory Node getResult() {
        return result;
    }

    public int getIndex() {
        return index;
    }
}
