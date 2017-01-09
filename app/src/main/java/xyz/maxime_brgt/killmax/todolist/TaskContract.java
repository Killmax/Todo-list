package xyz.maxime_brgt.killmax.todolist;

import android.provider.BaseColumns;

/**
 * Created by killmax on 1/9/17.
 */

public class TaskContract {
    public static final String DB_NAME = "xyz.maxime_brgt.killmax.todolist.db";
    public static final int DB_VERSION = 1;

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }
}
