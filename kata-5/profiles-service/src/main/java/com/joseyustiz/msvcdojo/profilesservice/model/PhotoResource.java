package com.joseyustiz.msvcdojo.profilesservice.model;

import org.springframework.core.io.AbstractResource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jyustiz on 11/4/18 for project microservice-dojo-katas.
 */
public class PhotoResource extends AbstractResource {

    private final Photo photo;

    public PhotoResource(Photo photo) {
        Assert.notNull(photo, "Photo must not be null");
        this.photo = photo;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return this.photo.getInputStream();
    }

    @Override
    public long contentLength() throws IOException {
        return -1;
    }

    public interface Photo {

        /**
         * @return a new {@link InputStream} containing photo data as a JPEG. The caller is
         * responsible for closing the stream.
         * @throws IOException
         */
        public InputStream getInputStream() throws IOException;

    }

}