package androidx.compose.foundation.text;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TextFieldSize.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0015\u0010-\u001a\u00020+2\u0006\u0010\n\u001a\u00020\u0001¢\u0006\u0004\b.\u0010/J8\u00100\u001a\u0002012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0001J\u000e\u00102\u001a\u0002012\u0006\u0010\n\u001a\u00020\u0001J\u0015\u00103\u001a\u00020+2\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b4\u00105R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\n\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R+\u0010#\u001a\u00020\"2\u0006\u0010!\u001a\u00020\"8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b(\u0010)\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u0010\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0004\n\u0002\u0010,¨\u00066"}, d2 = {"Landroidx/compose/foundation/text/TextFieldSize;", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "resolvedStyle", "Landroidx/compose/ui/text/TextStyle;", "typeface", "<init>", "(Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/ui/text/TextStyle;Ljava/lang/Object;)V", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "getDensity", "()Landroidx/compose/ui/unit/Density;", "setDensity", "(Landroidx/compose/ui/unit/Density;)V", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "setFontFamilyResolver", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "getResolvedStyle", "()Landroidx/compose/ui/text/TextStyle;", "setResolvedStyle", "(Landroidx/compose/ui/text/TextStyle;)V", "getTypeface", "()Ljava/lang/Object;", "setTypeface", "(Ljava/lang/Object;)V", "<set-?>", "", "dirty", "getDirty", "()Z", "setDirty", "(Z)V", "dirty$delegate", "Landroidx/compose/runtime/MutableState;", "minSize", "Landroidx/compose/ui/unit/IntSize;", "J", "cachedMinSizeOrComputeMinSize", "cachedMinSizeOrComputeMinSize-YEO4UFw", "(Ljava/lang/Object;)J", "update", "", "updateTypeface", "computeMinSize", "computeMinSize-YEO4UFw", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)J", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class TextFieldSize {
    private Density density;

    /* JADX INFO: renamed from: dirty$delegate, reason: from kotlin metadata */
    private final MutableState dirty = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
    private FontFamily.Resolver fontFamilyResolver;
    private LayoutDirection layoutDirection;
    private long minSize;
    private TextStyle resolvedStyle;
    private Object typeface;

    public TextFieldSize(LayoutDirection layoutDirection, Density density, FontFamily.Resolver fontFamilyResolver, TextStyle resolvedStyle, Object typeface) {
        this.layoutDirection = layoutDirection;
        this.density = density;
        this.fontFamilyResolver = fontFamilyResolver;
        this.resolvedStyle = resolvedStyle;
        this.typeface = typeface;
        this.minSize = m1671computeMinSizeYEO4UFw(this.fontFamilyResolver);
    }

    public final LayoutDirection getLayoutDirection() {
        return this.layoutDirection;
    }

    public final void setLayoutDirection(LayoutDirection layoutDirection) {
        this.layoutDirection = layoutDirection;
    }

    public final Density getDensity() {
        return this.density;
    }

    public final void setDensity(Density density) {
        this.density = density;
    }

    public final FontFamily.Resolver getFontFamilyResolver() {
        return this.fontFamilyResolver;
    }

    public final void setFontFamilyResolver(FontFamily.Resolver resolver) {
        this.fontFamilyResolver = resolver;
    }

    public final TextStyle getResolvedStyle() {
        return this.resolvedStyle;
    }

    public final void setResolvedStyle(TextStyle textStyle) {
        this.resolvedStyle = textStyle;
    }

    public final Object getTypeface() {
        return this.typeface;
    }

    public final void setTypeface(Object obj) {
        this.typeface = obj;
    }

    private final boolean getDirty() {
        State $this$getValue$iv = this.dirty;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    private final void setDirty(boolean z) {
        MutableState $this$setValue$iv = this.dirty;
        $this$setValue$iv.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: cachedMinSizeOrComputeMinSize-YEO4UFw, reason: not valid java name */
    public final long m1670cachedMinSizeOrComputeMinSizeYEO4UFw(Object typeface) {
        updateTypeface(typeface);
        if (getDirty()) {
            this.minSize = m1671computeMinSizeYEO4UFw(this.fontFamilyResolver);
            setDirty(false);
        }
        return this.minSize;
    }

    public static /* synthetic */ void update$default(TextFieldSize textFieldSize, LayoutDirection layoutDirection, Density density, FontFamily.Resolver resolver, TextStyle textStyle, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            layoutDirection = textFieldSize.layoutDirection;
        }
        if ((i & 2) != 0) {
            density = textFieldSize.density;
        }
        if ((i & 4) != 0) {
            resolver = textFieldSize.fontFamilyResolver;
        }
        if ((i & 8) != 0) {
            textStyle = textFieldSize.resolvedStyle;
        }
        textFieldSize.update(layoutDirection, density, resolver, textStyle, (i & 16) != 0 ? textFieldSize.typeface : obj);
    }

    public final void update(LayoutDirection layoutDirection, Density density, FontFamily.Resolver fontFamilyResolver, TextStyle resolvedStyle, Object typeface) {
        if (layoutDirection != this.layoutDirection || !Intrinsics.areEqual(density, this.density) || !Intrinsics.areEqual(fontFamilyResolver, this.fontFamilyResolver) || !Intrinsics.areEqual(resolvedStyle, this.resolvedStyle)) {
            this.layoutDirection = layoutDirection;
            this.density = density;
            this.fontFamilyResolver = fontFamilyResolver;
            this.resolvedStyle = resolvedStyle;
            setDirty(true);
            return;
        }
        updateTypeface(typeface);
    }

    public final void updateTypeface(Object typeface) {
        if (!Intrinsics.areEqual(typeface, this.typeface)) {
            this.typeface = typeface;
            setDirty(true);
        }
    }

    /* JADX INFO: renamed from: computeMinSize-YEO4UFw, reason: not valid java name */
    public final long m1671computeMinSizeYEO4UFw(FontFamily.Resolver fontFamilyResolver) {
        return TextFieldDelegateKt.computeSizeForDefaultText$default(this.resolvedStyle, this.density, fontFamilyResolver, null, 0, 24, null);
    }
}
