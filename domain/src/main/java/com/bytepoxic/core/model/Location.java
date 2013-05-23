package com.bytepoxic.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(finders = { "findLocationsByParent", "findLocationsByName", "findLocationsByCode" })
public class Location extends BaseEntity implements Comparable<com.bytepoxic.core.model.Location> {

    @ManyToOne
    private com.bytepoxic.core.model.Location parent;

    @NotNull
    @Size(max = 64)
    private String name;

    @Column(unique = true)
    @Size(max = 2)
    private String code;

    @NotNull
    private Boolean appCurrent;

    private Double latitude;

    private Double longitude;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    @Sort(type = SortType.NATURAL)
    @Fetch(FetchMode.SUBSELECT)
    private List<com.bytepoxic.core.model.Location> children = new ArrayList<com.bytepoxic.core.model.Location>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "country", optional = true)
    @JoinColumn(name = "country", nullable = true)
    @Fetch(FetchMode.SELECT)
    private Nationality nationality;

    @Size(max = 128)
    private String labelKey;

    public static TypedQuery<com.bytepoxic.core.model.Location> findMainLocations() {
        EntityManager em = Location.entityManager();
        TypedQuery<Location> q = em.createQuery("SELECT o FROM Location AS o WHERE o.parent is null and o.deleted = false", Location.class);
        return q;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Location other = (Location) obj;
        if (getId() == null) {
            if (other.getId() != null) return false;
        } else if (!getId().equals(other.getId())) return false;
        if (code == null) {
            if (other.code != null) return false;
        } else if (!code.equals(other.code)) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }

    public int compareTo(com.bytepoxic.core.model.Location loc) {
        if (loc == null || loc.name == null) {
            return 1;
        } else if (name == null) {
            return -1;
        }
        return name.compareTo(loc.name);
    }

    public boolean hasValidCoords() {
        return latitude != null && longitude != null;
    }

    public boolean isMapValid() {
        return hasValidCoords();
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean hasChildren() {
        return children != null && !children.isEmpty();
    }

    public com.bytepoxic.core.model.Location findLocationById(Long id) {
        if (this.getId() != null && this.getId().equals(id)) {
            return this;
        }
        if (hasChildren()) {
            for (Location child : children) {
                Location found = child.findLocationById(id);
                if (found != null) {
                    return found;
                }
            }
        }
        return null;
    }

    public void addLocation(com.bytepoxic.core.model.Location loc) {
        if (children == null) {
            children = new ArrayList<Location>();
        }
        children.add(loc);
    }

    public void updateLocation(com.bytepoxic.core.model.Location newLoc) {
        Location loc = findLocationById(newLoc.getId());
        if (loc != null) {
            if (loc.parent != null) {
                loc.parent.children.remove(loc);
                loc.parent.children.add(newLoc);
            }
            if (loc.children != null) {
                for (Location child : children) {
                    child.parent = newLoc;
                }
            }
            newLoc.parent = loc.parent;
            newLoc.children = loc.children;
        }
    }

    public void removeLocation() {
        while (hasChildren()) {
            Location child = children.get(0);
            child.removeLocation();
            child = null;
        }
        if (parent != null) {
            parent.children.remove(this);
            if (parent.children.isEmpty()) {
                parent.children = null;
            }
            parent = null;
        }
    }

    public List<com.bytepoxic.core.model.Location> getChildrenAsList() {
        return new ArrayList<Location>(children);
    }
}
