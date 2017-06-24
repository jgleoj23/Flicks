
package com.example.jgardi.flicks.model;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.InjectionUtil;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2017-06-23T17:35-0700")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class Config$$Parcelable
    implements Parcelable, ParcelWrapper<com.example.jgardi.flicks.model.Config>
{

    private com.example.jgardi.flicks.model.Config config$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<Config$$Parcelable>CREATOR = new Creator<Config$$Parcelable>() {


        @Override
        public Config$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Config$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Config$$Parcelable[] newArray(int size) {
            return new Config$$Parcelable[size] ;
        }

    }
    ;

    public Config$$Parcelable(com.example.jgardi.flicks.model.Config config$$2) {
        config$$0 = config$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(config$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.example.jgardi.flicks.model.Config config$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(config$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(config$$1));
            parcel$$1 .writeString(InjectionUtil.getField(java.lang.String.class, com.example.jgardi.flicks.model.Config.class, config$$1, "posterSize"));
            parcel$$1 .writeString(InjectionUtil.getField(java.lang.String.class, com.example.jgardi.flicks.model.Config.class, config$$1, "imageBaseUrl"));
            parcel$$1 .writeString(InjectionUtil.getField(java.lang.String.class, com.example.jgardi.flicks.model.Config.class, config$$1, "backdropSize"));
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.example.jgardi.flicks.model.Config getParcel() {
        return config$$0;
    }

    public static com.example.jgardi.flicks.model.Config read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.example.jgardi.flicks.model.Config config$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            config$$4 = new com.example.jgardi.flicks.model.Config();
            identityMap$$1 .put(reservation$$0, config$$4);
            InjectionUtil.setField(com.example.jgardi.flicks.model.Config.class, config$$4, "posterSize", parcel$$3 .readString());
            InjectionUtil.setField(com.example.jgardi.flicks.model.Config.class, config$$4, "imageBaseUrl", parcel$$3 .readString());
            InjectionUtil.setField(com.example.jgardi.flicks.model.Config.class, config$$4, "backdropSize", parcel$$3 .readString());
            com.example.jgardi.flicks.model.Config config$$3 = config$$4;
            identityMap$$1 .put(identity$$1, config$$3);
            return config$$3;
        }
    }

}
