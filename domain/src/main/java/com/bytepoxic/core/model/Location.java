package com.bytepoxic.core.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity
public class Location extends BaseEntity implements Comparable<Location> {
    @ManyToOne
    private Location parent;

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
    private SortedSet<Location> children = new TreeSet<Location>();

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "country", optional = true)
    @JoinColumn(name = "country", nullable = true)
    @Fetch(FetchMode.SELECT)
    private Nationality nationality;

    @Size(max = 128)
    private String labelKey;

    /*public static TypedQuery<com.bytepoxic.core.model.Location> findMainLocations() {
        EntityManager em = Location.entityManager();
        TypedQuery<Location> q = em.createQuery("SELECT o FROM Location AS o WHERE o.parent is null and o.deleted = false", Location.class);
        return q;
    }*/

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

    public Location findLocationById(Long id) {
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

    public void addLocation(Location loc) {
        if (children == null) {
            children = new TreeSet<Location>();
        }
        children.add(loc);
    }

    public void updateLocation(Location newLoc) {
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
        	for(Location child : children) {
        		child.removeLocation();
                child = null;
        	}
        }
        if (parent != null) {
            parent.children.remove(this);
            if (parent.children.isEmpty()) {
                parent.children = null;
            }
            parent = null;
        }
    }

    public List<Location> getChildrenAsList() {
        return new ArrayList<Location>(children);
    }
}
