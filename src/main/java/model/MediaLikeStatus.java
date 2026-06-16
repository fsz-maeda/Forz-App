package model;

import java.io.Serializable;

public class MediaLikeStatus implements Serializable {
    private int likes;
    private boolean liked;

    public MediaLikeStatus(int likes, boolean liked) {
        this.likes = likes;
        this.liked = liked;
    }

    public int getLikes() {
        return likes;
    }

    public boolean isLiked() {
        return liked;
    }
}