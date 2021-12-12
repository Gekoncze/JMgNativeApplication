package cz.mg.nativeapplication.explorer;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Part;
import cz.mg.entity.mapper.Mapper;
import cz.mg.nativeapplication.explorer.history.TransactionManager;

import java.nio.file.Path;


public @Utility class Explorer {
    private final @Mandatory @Part TransactionManager transactionManager = new TransactionManager();
    private final @Mandatory Mapper mapper;
    private @Optional @Part Object root;
    private @Optional @Part Path path;

    public Explorer(@Mandatory Mapper mapper) {
        this.mapper = mapper;
    }

    public @Mandatory TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public @Mandatory Mapper getMapper() {
        return mapper;
    }

    public @Optional Object getRoot() {
        return root;
    }

    public void setRoot(@Optional Object root) {
        this.root = root;
    }

    public @Optional Path getPath() {
        return path;
    }

    public void setPath(@Optional Path path) {
        this.path = path;
    }
}
