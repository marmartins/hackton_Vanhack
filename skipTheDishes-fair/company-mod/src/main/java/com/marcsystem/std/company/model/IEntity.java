package com.marcsystem.std.company.model;

import java.io.Serializable;

public interface IEntity<T extends Serializable> {

    T getId();

}
