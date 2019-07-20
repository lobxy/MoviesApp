
package com.lobxy.moviesapp.Model.MovieSingleDetails;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Credits {

    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

}
