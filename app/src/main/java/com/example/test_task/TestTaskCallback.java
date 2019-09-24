package com.example.test_task;

import java.util.List;

public interface TestTaskCallback {
    void onGetListACallback (List<ResponseA> response);
    void onGetItemBCallback (ResponseB response);
}
