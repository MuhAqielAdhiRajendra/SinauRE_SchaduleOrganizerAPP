package androidx.window.layout.adapter.sidecar;

import android.os.IBinder;
import androidx.window.sidecar.SidecarDeviceState;
import androidx.window.sidecar.SidecarInterface;
import androidx.window.sidecar.SidecarWindowLayoutInfo;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes13.dex */
public class DistinctElementSidecarCallback implements SidecarInterface.SidecarCallback {
    private final Map<IBinder, SidecarWindowLayoutInfo> mActivityWindowLayoutInfo;
    private final SidecarAdapter mAdapter;
    private final SidecarInterface.SidecarCallback mCallback;
    private SidecarDeviceState mLastDeviceState;
    private final Object mLock;

    DistinctElementSidecarCallback(SidecarAdapter adapter, SidecarInterface.SidecarCallback callback) {
        this.mLock = new Object();
        this.mActivityWindowLayoutInfo = new WeakHashMap();
        this.mAdapter = adapter;
        this.mCallback = callback;
    }

    public DistinctElementSidecarCallback(SidecarInterface.SidecarCallback callback) {
        this.mLock = new Object();
        this.mActivityWindowLayoutInfo = new WeakHashMap();
        this.mAdapter = new SidecarAdapter();
        this.mCallback = callback;
    }

    public void onDeviceStateChanged(SidecarDeviceState newDeviceState) {
        if (newDeviceState == null) {
            return;
        }
        synchronized (this.mLock) {
            if (this.mAdapter.isEqualSidecarDeviceState(this.mLastDeviceState, newDeviceState)) {
                return;
            }
            this.mLastDeviceState = newDeviceState;
            this.mCallback.onDeviceStateChanged(newDeviceState);
        }
    }

    public void onWindowLayoutChanged(IBinder windowToken, SidecarWindowLayoutInfo newLayout) {
        synchronized (this.mLock) {
            SidecarWindowLayoutInfo lastInfo = this.mActivityWindowLayoutInfo.get(windowToken);
            if (this.mAdapter.isEqualSidecarWindowLayoutInfo(lastInfo, newLayout)) {
                return;
            }
            this.mActivityWindowLayoutInfo.put(windowToken, newLayout);
            this.mCallback.onWindowLayoutChanged(windowToken, newLayout);
        }
    }
}
