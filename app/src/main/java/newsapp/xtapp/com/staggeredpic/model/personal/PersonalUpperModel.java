package newsapp.xtapp.com.staggeredpic.model.personal;

import android.support.annotation.NonNull;

import newsapp.xtapp.com.staggeredpic.contract.personal.PersonalContract;


/**
 * Created by Horrarndoo on 2017/9/26.
 * <p>
 */

public class PersonalUpperModel implements PersonalContract.IPersonalUpperModel {

    @NonNull
    public static PersonalUpperModel newInstance() {
        return new PersonalUpperModel();
    }
}
