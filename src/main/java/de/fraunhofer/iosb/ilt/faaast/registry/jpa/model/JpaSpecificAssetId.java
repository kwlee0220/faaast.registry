package de.fraunhofer.iosb.ilt.faaast.registry.jpa.model;

import java.util.Objects;

import org.eclipse.digitaltwin.aas4j.v3.model.builder.ExtendableBuilder;
import org.eclipse.digitaltwin.aas4j.v3.model.impl.DefaultSpecificAssetId;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Kang-Woo Lee (ETRI)
 */
public class JpaSpecificAssetId extends DefaultSpecificAssetId {
    @JsonIgnore
    private String id;

    public JpaSpecificAssetId() {
        id = null;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        else if (obj == null) {
            return false;
        }
        else if (this.getClass() != obj.getClass()) {
            return false;
        }
        else {
        	JpaSpecificAssetId other = (JpaSpecificAssetId) obj;
            return super.equals(obj)
                    && Objects.equals(this.id, other.id);
        }
    }

    public abstract static class AbstractBuilder<T extends JpaSpecificAssetId, B extends AbstractBuilder<T, B>>
            extends ExtendableBuilder<JpaSpecificAssetId, B> {
        public B id(String value) {
            getBuildingInstance().setId(value);
            return getSelf();
        }

        public B name(String name) {
            getBuildingInstance().setName(name);
            return getSelf();
        }

        public B value(String value) {
            getBuildingInstance().setValue(value);
            return getSelf();
        }

        public B from(JpaSpecificAssetId other) {
            if (Objects.nonNull(other)) {
                value(other.getValue());
                name(other.getName());
            }
            return getSelf();
        }
    }

    public static class Builder extends AbstractBuilder<JpaSpecificAssetId, Builder> {
        @Override
        protected Builder getSelf() {
            return this;
        }

        @Override
        protected JpaSpecificAssetId newBuildingInstance() {
            return new JpaSpecificAssetId();
        }
    }
}
