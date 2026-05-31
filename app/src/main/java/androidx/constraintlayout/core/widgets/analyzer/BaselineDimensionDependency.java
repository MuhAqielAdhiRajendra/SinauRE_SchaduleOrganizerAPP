package androidx.constraintlayout.core.widgets.analyzer;

/* JADX INFO: loaded from: classes12.dex */
class BaselineDimensionDependency extends DimensionDependency {
    BaselineDimensionDependency(WidgetRun run) {
        super(run);
    }

    public void update(DependencyNode node) {
        VerticalWidgetRun verticalRun = (VerticalWidgetRun) this.mRun;
        verticalRun.baseline.mMargin = this.mRun.mWidget.getBaselineDistance();
        this.resolved = true;
    }
}
