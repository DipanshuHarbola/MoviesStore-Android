package com.deepanshu.moviestore.storeone.models;

public class Movies {
    private String duration;

    private String title;

    private String _id;

    private String image_url;

    private String stars;

    private String description;

    private String release_date;

    private String rating;

    private String writer;

    private String director;

    private String movie_type;

    public String getDuration ()
    {
        return duration;
    }

    public void setDuration (String duration)
    {
        this.duration = duration;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String getImage_url ()
    {
        return image_url;
    }

    public void setImage_url (String image_url)
    {
        this.image_url = image_url;
    }

    public String getStars ()
    {
        return stars;
    }

    public void setStars (String stars)
    {
        this.stars = stars;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getRelease_date ()
    {
        return release_date;
    }

    public void setRelease_date (String release_date)
    {
        this.release_date = release_date;
    }

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public String getWriter ()
    {
        return writer;
    }

    public void setWriter (String writer)
    {
        this.writer = writer;
    }

    public String getDirector ()
    {
        return director;
    }

    public void setDirector (String director)
    {
        this.director = director;
    }

    public String getMovie_type ()
    {
        return movie_type;
    }

    public void setMovie_type (String movie_type)
    {
        this.movie_type = movie_type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [duration = "+duration+", title = "+title+", _id = "+_id+", image_url = "+image_url+", stars = "+stars+", description = "+description+", release_date = "+release_date+", rating = "+rating+", writer = "+writer+", director = "+director+", movie_type = "+movie_type+"]";
    }


}
