package com.gala.gaetano.openapidemo.app.exception;

import com.gala.gaetano.openapidemo.api.model.NoDataFoundError;
import lombok.Getter;
import lombok.Setter;

public class NoDataException extends Exception{

    @Getter
    @Setter
    private NoDataFoundError error;

    public NoDataException(NoDataFoundError error){
        this.error = error;
    }
}
