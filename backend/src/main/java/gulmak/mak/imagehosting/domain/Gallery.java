package gulmak.mak.imagehosting.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="galleries")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gallery_id")
    private Integer id;

    @Column
    private String name;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                          CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                           CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name="image_gallery",
                joinColumns = @JoinColumn(name = "gallery_id"),
                inverseJoinColumns = @JoinColumn(name = "image_id"))
    private Set<ImageElement> images;

    @Column
    private LocalDateTime created;

    public Gallery() {
    }

    public void addImages(Set<ImageElement> images){
        this.images.addAll(images);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<ImageElement> getImages() {
        if (this.images == null) this.images = new HashSet();
        return images;
    }

    public void setImages(Set<ImageElement> images) {
        this.images = images;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gallery)) return false;

        Gallery gallery = (Gallery) o;

        if (id != null ? !id.equals(gallery.id) : gallery.id != null) return false;
        if (name != null ? !name.equals(gallery.name) : gallery.name != null) return false;
        if (user != null ? !user.equals(gallery.user) : gallery.user != null) return false;
        if (images != null ? !images.equals(gallery.images) : gallery.images != null) return false;
        return created != null ? created.equals(gallery.created) : gallery.created == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", images=" + images +
                ", created=" + created +
                '}';
    }
}
