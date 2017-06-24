
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
public class Movie$$Parcelable
    implements Parcelable, ParcelWrapper<com.example.jgardi.flicks.model.Movie>
{

    private com.example.jgardi.flicks.model.Movie movie$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<Movie$$Parcelable>CREATOR = new Creator<Movie$$Parcelable>() {


        @Override
        public Movie$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new Movie$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public Movie$$Parcelable[] newArray(int size) {
            return new Movie$$Parcelable[size] ;
        }

    }
    ;

    public Movie$$Parcelable(com.example.jgardi.flicks.model.Movie movie$$2) {
        movie$$0 = movie$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(movie$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.example.jgardi.flicks.model.Movie movie$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(movie$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(movie$$1));
            parcel$$1 .writeString(InjectionUtil.getField(java.lang.String.class, com.example.jgardi.flicks.model.Movie.class, movie$$1, "overview"));
            if (InjectionUtil.getField(java.lang.Double.class, com.example.jgardi.flicks.model.Movie.class, movie$$1, "voteAverage") == null) {
                parcel$$1 .writeInt(-1);
            } else {
                parcel$$1 .writeInt(1);
                parcel$$1 .writeDouble(InjectionUtil.getField(java.lang.Double.class, com.example.jgardi.flicks.model.Movie.class, movie$$1, "voteAverage"));
            }
            parcel$$1 .writeString(InjectionUtil.getField(java.lang.String.class, com.example.jgardi.flicks.model.Movie.class, movie$$1, "backdropPath"));
            parcel$$1 .writeInt(InjectionUtil.getField(int.class, com.example.jgardi.flicks.model.Movie.class, movie$$1, "id"));
            parcel$$1 .writeString(InjectionUtil.getField(java.lang.String.class, com.example.jgardi.flicks.model.Movie.class, movie$$1, "title"));
            parcel$$1 .writeString(InjectionUtil.getField(java.lang.String.class, com.example.jgardi.flicks.model.Movie.class, movie$$1, "posterPath"));
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.example.jgardi.flicks.model.Movie getParcel() {
        return movie$$0;
    }

    public static com.example.jgardi.flicks.model.Movie read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.example.jgardi.flicks.model.Movie movie$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            movie$$4 = new com.example.jgardi.flicks.model.Movie();
            identityMap$$1 .put(reservation$$0, movie$$4);
            InjectionUtil.setField(com.example.jgardi.flicks.model.Movie.class, movie$$4, "overview", parcel$$3 .readString());
            int int$$0 = parcel$$3 .readInt();
            java.lang.Double double$$0;
            if (int$$0 < 0) {
                double$$0 = null;
            } else {
                double$$0 = parcel$$3 .readDouble();
            }
            InjectionUtil.setField(com.example.jgardi.flicks.model.Movie.class, movie$$4, "voteAverage", double$$0);
            InjectionUtil.setField(com.example.jgardi.flicks.model.Movie.class, movie$$4, "backdropPath", parcel$$3 .readString());
            InjectionUtil.setField(com.example.jgardi.flicks.model.Movie.class, movie$$4, "id", parcel$$3 .readInt());
            InjectionUtil.setField(com.example.jgardi.flicks.model.Movie.class, movie$$4, "title", parcel$$3 .readString());
            InjectionUtil.setField(com.example.jgardi.flicks.model.Movie.class, movie$$4, "posterPath", parcel$$3 .readString());
            com.example.jgardi.flicks.model.Movie movie$$3 = movie$$4;
            identityMap$$1 .put(identity$$1, movie$$3);
            return movie$$3;
        }
    }

}
