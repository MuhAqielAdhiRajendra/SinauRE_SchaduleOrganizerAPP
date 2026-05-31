package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.ComposeUiFlags;
import androidx.compose.ui.FrameRateCategory;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.MutableRectKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.MatrixKt;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.input.pointer.MatrixPositionCalculator;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsOwnerKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: NodeCoordinator.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000À\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b \b!\u0018\u0000 Ë\u00022\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0004Ê\u0002Ë\u0002B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0012\u00103\u001a\u0004\u0018\u00010\u00152\u0006\u00104\u001a\u00020\fH\u0002J-\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u00104\u001a\u00020\f2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u0002060:H\u0086\bJ:\u00105\u001a\u000206\"\u0006\b\u0000\u0010;\u0018\u00012\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H;0=2\u0012\u00109\u001a\u000e\u0012\u0004\u0012\u0002H;\u0012\u0004\u0012\u0002060:H\u0086\b¢\u0006\u0004\b>\u0010?J\u001b\u0010@\u001a\u00020\f2\n\u0010<\u001a\u0006\u0012\u0002\b\u00030=H\u0002¢\u0006\u0004\bA\u0010BJ\u001b\u0010C\u001a\u0004\u0018\u00010\u00152\n\u0010<\u001a\u0006\u0012\u0002\b\u00030=¢\u0006\u0004\bD\u0010EJ\u0006\u0010U\u001a\u00020\fJ\r\u0010\\\u001a\u000206H\u0010¢\u0006\u0002\b]J\b\u0010q\u001a\u000206H&J\u0018\u0010v\u001a\u0002062\u0006\u0010w\u001a\u0002082\u0006\u0010x\u001a\u000208H\u0014J\u000f\u0010\u0087\u0001\u001a\u000206H\u0000¢\u0006\u0003\b\u0088\u0001J0\u0010§\u0001\u001a\u00030¨\u00012\b\u0010©\u0001\u001a\u00030\u0099\u00012\u0010\b\u0004\u00109\u001a\n\u0012\u0005\u0012\u00030¨\u00010ª\u0001H\u0084\b¢\u0006\u0006\b«\u0001\u0010¬\u0001J\u0007\u0010\u00ad\u0001\u001a\u000206J\u0007\u0010®\u0001\u001a\u000206J=\u0010¯\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020y2\u0006\u0010\u007f\u001a\u00020%2\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bMH\u0014¢\u0006\u0006\b°\u0001\u0010±\u0001J,\u0010¯\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020y2\u0006\u0010\u007f\u001a\u00020%2\b\u0010²\u0001\u001a\u00030³\u0001H\u0014¢\u0006\u0006\b°\u0001\u0010´\u0001JI\u0010µ\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020y2\u0006\u0010\u007f\u001a\u00020%2\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM2\n\u0010¶\u0001\u001a\u0005\u0018\u00010³\u0001H\u0002¢\u0006\u0006\b·\u0001\u0010¸\u0001J\u0007\u0010¹\u0001\u001a\u000206JG\u0010º\u0001\u001a\u0002062\u0006\u0010z\u001a\u00020y2\u0006\u0010\u007f\u001a\u00020%2\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM2\n\u0010²\u0001\u001a\u0005\u0018\u00010³\u0001¢\u0006\u0006\b»\u0001\u0010¸\u0001J\u001d\u0010¼\u0001\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\n\u0010¿\u0001\u001a\u0005\u0018\u00010³\u0001J\u001f\u0010À\u0001\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\n\u0010¿\u0001\u001a\u0005\u0018\u00010³\u0001H\u0002J\u001f\u0010Á\u0001\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\n\u0010¿\u0001\u001a\u0005\u0018\u00010³\u0001H\u0016J\u0007\u0010Â\u0001\u001a\u000206J-\u0010Ê\u0001\u001a\u0002062\u0019\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM2\t\b\u0002\u0010Ë\u0001\u001a\u00020\fJ\u0014\u0010Ì\u0001\u001a\u0002062\t\b\u0002\u0010Í\u0001\u001a\u00020\fH\u0002JA\u0010Ø\u0001\u001a\u0002062\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\f¢\u0006\u0006\bâ\u0001\u0010ã\u0001JI\u0010ä\u0001\u001a\u000206*\u0004\u0018\u00010\u00152\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bå\u0001\u0010æ\u0001J[\u0010ç\u0001\u001a\u000206*\u0004\u0018\u00010\u00152\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\f2\u0007\u0010è\u0001\u001a\u00020%2\u0007\u0010é\u0001\u001a\u00020\fH\u0002¢\u0006\u0006\bê\u0001\u0010ë\u0001JR\u0010ì\u0001\u001a\u000206*\u0004\u0018\u00010\u00152\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\f2\u0007\u0010è\u0001\u001a\u00020%H\u0002¢\u0006\u0006\bí\u0001\u0010î\u0001JR\u0010ï\u0001\u001a\u000206*\u0004\u0018\u00010\u00152\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\f2\u0007\u0010è\u0001\u001a\u00020%H\u0002¢\u0006\u0006\bð\u0001\u0010î\u0001J,\u0010ñ\u0001\u001a\u00020\f*\u0004\u0018\u00010\u00152\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010ß\u0001\u001a\u00030à\u0001H\u0002¢\u0006\u0006\bò\u0001\u0010ó\u0001JC\u0010ô\u0001\u001a\u0002062\b\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\b\u0010ß\u0001\u001a\u00030à\u00012\u0007\u0010á\u0001\u001a\u00020\fH\u0016¢\u0006\u0006\bõ\u0001\u0010ã\u0001J\b\u0010ö\u0001\u001a\u00030÷\u0001J\u001d\u0010ø\u0001\u001a\u00030Ü\u00012\b\u0010ù\u0001\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\bú\u0001\u0010û\u0001J\u001d\u0010ü\u0001\u001a\u00030Ü\u00012\b\u0010ý\u0001\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\bþ\u0001\u0010û\u0001J\u001d\u0010ÿ\u0001\u001a\u00030Ü\u00012\b\u0010\u0080\u0002\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\b\u0081\u0002\u0010û\u0001J\u001d\u0010\u0082\u0002\u001a\u00030Ü\u00012\b\u0010ý\u0001\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\b\u0083\u0002\u0010û\u0001J\r\u0010\u0084\u0002\u001a\u00020\u0000*\u00020\u0003H\u0002J&\u0010\u0085\u0002\u001a\u00030Ü\u00012\u0007\u0010\u0086\u0002\u001a\u00020\u00032\b\u0010\u0087\u0002\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\b\u0088\u0002\u0010\u0089\u0002J/\u0010\u0085\u0002\u001a\u00030Ü\u00012\u0007\u0010\u0086\u0002\u001a\u00020\u00032\b\u0010\u0087\u0002\u001a\u00030Ü\u00012\u0007\u0010\u008a\u0002\u001a\u00020\fH\u0016¢\u0006\u0006\b\u008b\u0002\u0010\u008c\u0002J%\u0010\u008d\u0002\u001a\u0002062\u0007\u0010\u0086\u0002\u001a\u00020\u00032\b\u0010\u008e\u0002\u001a\u00030\u008f\u0002H\u0016¢\u0006\u0006\b\u0090\u0002\u0010\u0091\u0002J\u001c\u0010\u0092\u0002\u001a\u0002062\b\u0010\u008e\u0002\u001a\u00030\u008f\u0002H\u0016¢\u0006\u0006\b\u0093\u0002\u0010\u0094\u0002J%\u0010\u0095\u0002\u001a\u0002062\u0007\u0010\u0096\u0002\u001a\u00020\u00002\b\u0010\u008e\u0002\u001a\u00030\u008f\u0002H\u0002¢\u0006\u0006\b\u0097\u0002\u0010\u0098\u0002J%\u0010\u0099\u0002\u001a\u0002062\u0007\u0010\u0096\u0002\u001a\u00020\u00002\b\u0010\u008e\u0002\u001a\u00030\u008f\u0002H\u0002¢\u0006\u0006\b\u009a\u0002\u0010\u0098\u0002J\u001c\u0010\u009b\u0002\u001a\u00030÷\u00012\u0007\u0010\u0086\u0002\u001a\u00020\u00032\u0007\u0010\u009c\u0002\u001a\u00020\fH\u0016J/\u0010\u009d\u0002\u001a\u00030Ü\u00012\u0007\u0010\u0096\u0002\u001a\u00020\u00002\b\u0010\u009e\u0002\u001a\u00030Ü\u00012\u0007\u0010\u008a\u0002\u001a\u00020\fH\u0002¢\u0006\u0006\b\u009f\u0002\u0010 \u0002J%\u0010\u009d\u0002\u001a\u0002062\u0007\u0010\u0096\u0002\u001a\u00020\u00002\b\u0010¡\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u009c\u0002\u001a\u00020\fH\u0002J\u001d\u0010¢\u0002\u001a\u00030Ü\u00012\b\u0010ý\u0001\u001a\u00030Ü\u0001H\u0016¢\u0006\u0006\b£\u0002\u0010û\u0001J)\u0010¤\u0002\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\u0013\u00109\u001a\u000f\u0012\u0005\u0012\u00030¾\u0001\u0012\u0004\u0012\u0002060:H\u0084\bJ'\u0010¥\u0002\u001a\u00030Ü\u00012\u0007\u0010z\u001a\u00030Ü\u00012\t\b\u0002\u0010\u008a\u0002\u001a\u00020\fH\u0016¢\u0006\u0006\b¦\u0002\u0010§\u0002J'\u0010¨\u0002\u001a\u00030Ü\u00012\u0007\u0010z\u001a\u00030Ü\u00012\t\b\u0002\u0010\u008a\u0002\u001a\u00020\fH\u0016¢\u0006\u0006\b©\u0002\u0010§\u0002J\u001d\u0010ª\u0002\u001a\u0002062\b\u0010½\u0001\u001a\u00030¾\u00012\b\u0010«\u0002\u001a\u00030¬\u0002H\u0004J\u0007\u0010\u00ad\u0002\u001a\u000206J\u0007\u0010®\u0002\u001a\u000206J-\u0010¯\u0002\u001a\u0002062\b\u0010°\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u009c\u0002\u001a\u00020\f2\t\b\u0002\u0010±\u0002\u001a\u00020\fH\u0000¢\u0006\u0003\b²\u0002J\u001c\u0010³\u0002\u001a\u0002062\b\u0010°\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u009c\u0002\u001a\u00020\fH\u0002J\u001c\u0010´\u0002\u001a\u00020\f2\b\u0010Û\u0001\u001a\u00030Ü\u0001H\u0004¢\u0006\u0006\bµ\u0002\u0010¶\u0002J\u001c\u0010·\u0002\u001a\u00020\f2\b\u0010Û\u0001\u001a\u00030Ü\u0001H\u0004¢\u0006\u0006\b¸\u0002\u0010¶\u0002J\t\u0010¹\u0002\u001a\u000206H\u0016J\t\u0010º\u0002\u001a\u000206H\u0016J\u0018\u0010»\u0002\u001a\u00020\u00002\u0007\u0010¼\u0002\u001a\u00020\u0000H\u0000¢\u0006\u0003\b½\u0002J\u0007\u0010¾\u0002\u001a\u00020\fJ\u001d\u0010¿\u0002\u001a\u00030Ü\u00012\b\u0010Û\u0001\u001a\u00030Ü\u0001H\u0002¢\u0006\u0006\bÀ\u0002\u0010û\u0001J\u001d\u0010Á\u0002\u001a\u00030Ö\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0004¢\u0006\u0006\bÂ\u0002\u0010û\u0001J'\u0010Ã\u0002\u001a\u00030Ü\u00012\b\u0010Ä\u0002\u001a\u00030\u008e\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0004¢\u0006\u0006\bÅ\u0002\u0010Æ\u0002J&\u0010Ç\u0002\u001a\u00020%2\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u0001H\u0004¢\u0006\u0006\bÈ\u0002\u0010É\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0000X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u0000X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010'R\u0016\u0010*\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R\u0014\u00100\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u0010\u000eR\u000e\u00102\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010F\u001a\u00020G8F¢\u0006\u0006\u001a\u0004\bH\u0010IR\u000e\u0010J\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000RD\u0010N\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM2\u0019\u0010K\u001a\u0015\u0012\u0004\u0012\u00020L\u0012\u0004\u0012\u000206\u0018\u00010:¢\u0006\u0002\bM@BX\u0084\u000e¢\u0006\b\n\u0000\u001a\u0004\bO\u0010PR\u000e\u0010Q\u001a\u00020RX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010V\u001a\u00020W8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0016\u0010Z\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010,R\u0014\u0010^\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b_\u0010\u000eR\u0014\u0010`\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b`\u0010\u000eR\u0010\u0010a\u001a\u0004\u0018\u00010bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010c\u001a\u00020b2\u0006\u0010K\u001a\u00020b8P@PX\u0090\u000e¢\u0006\f\u001a\u0004\bd\u0010e\"\u0004\bf\u0010gR&\u0010i\u001a\u0004\u0018\u00010h2\b\u0010K\u001a\u0004\u0018\u00010h@dX¦\u000e¢\u0006\f\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u0016\u0010n\u001a\n\u0012\u0004\u0012\u00020p\u0018\u00010oX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010r\u001a\b\u0012\u0004\u0012\u00020p0s8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bt\u0010uR&\u0010z\u001a\u00020y2\u0006\u0010K\u001a\u00020y@TX\u0096\u000e¢\u0006\u0010\n\u0002\u0010~\u001a\u0004\b{\u0010I\"\u0004\b|\u0010}R'\u0010\u007f\u001a\u00020%2\u0006\u0010K\u001a\u00020%@DX\u0086\u000e¢\u0006\u0011\n\u0000\u001a\u0005\b\u0080\u0001\u0010'\"\u0006\b\u0081\u0001\u0010\u0082\u0001R\u001a\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R\u0015\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0007\u001a\u0005\b\u008a\u0001\u0010/R\u0015\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0007\u001a\u0005\b\u008c\u0001\u0010/R\u0012\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u008f\u0001\u001a\u00030\u008e\u00018DX\u0084\u0004¢\u0006\b\u001a\u0006\b\u0090\u0001\u0010\u0091\u0001R\u0018\u0010\u0092\u0001\u001a\u00030\u0093\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u0012\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0097\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0098\u0001\u001a\u00030\u0099\u00018@X\u0080\u0004¢\u0006\u0007\u001a\u0005\b\u009a\u0001\u0010IR \u0010\u009b\u0001\u001a\u00030\u009c\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001\"\u0006\b\u009f\u0001\u0010 \u0001R\u001d\u0010¡\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¢\u0001\u0010\u000e\"\u0005\b£\u0001\u0010\u0010R\u001d\u0010¤\u0001\u001a\u00020\fX\u0080\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b¥\u0001\u0010\u000e\"\u0005\b¦\u0001\u0010\u0010R\u0012\u0010Ã\u0001\u001a\u0005\u0018\u00010³\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010Ä\u0001\u001a\u0005\u0018\u00010¾\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010Å\u0001\u001a\u001b\u0012\u0005\u0012\u00030¾\u0001\u0012\u0007\u0012\u0005\u0018\u00010³\u0001\u0012\u0004\u0012\u000206\u0018\u00010Æ\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010Ç\u0001\u001a\u0019\u0012\u0005\u0012\u00030¾\u0001\u0012\u0007\u0012\u0005\u0018\u00010³\u0001\u0012\u0004\u0012\u0002060Æ\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\bÈ\u0001\u0010É\u0001R\u0016\u0010Î\u0001\u001a\t\u0012\u0004\u0012\u0002060ª\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Ï\u0001\u001a\u00020\f2\u0006\u0010K\u001a\u00020\f@BX\u0080\u000e¢\u0006\t\n\u0000\u001a\u0005\bÐ\u0001\u0010\u000eR'\u0010²\u0001\u001a\u0005\u0018\u00010Ñ\u00012\t\u0010K\u001a\u0005\u0018\u00010Ñ\u0001@BX\u0086\u000e¢\u0006\n\n\u0000\u001a\u0006\bÒ\u0001\u0010Ó\u0001R\u0012\u0010¶\u0001\u001a\u0005\u0018\u00010³\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010Ô\u0001\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÔ\u0001\u0010\u000eR\u0014\u0010Õ\u0001\u001a\u00030Ö\u00018F¢\u0006\u0007\u001a\u0005\b×\u0001\u0010I¨\u0006Ì\u0002"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator;", "Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "Landroidx/compose/ui/layout/Measurable;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "Landroidx/compose/ui/node/OwnerScope;", "layoutNode", "Landroidx/compose/ui/node/LayoutNode;", "<init>", "(Landroidx/compose/ui/node/LayoutNode;)V", "getLayoutNode", "()Landroidx/compose/ui/node/LayoutNode;", "forcePlaceWithLookaheadOffset", "", "getForcePlaceWithLookaheadOffset$ui", "()Z", "setForcePlaceWithLookaheadOffset$ui", "(Z)V", "forceMeasureWithLookaheadConstraints", "getForceMeasureWithLookaheadConstraints$ui", "setForceMeasureWithLookaheadConstraints$ui", "tail", "Landroidx/compose/ui/Modifier$Node;", "getTail", "()Landroidx/compose/ui/Modifier$Node;", "wrapped", "getWrapped$ui", "()Landroidx/compose/ui/node/NodeCoordinator;", "setWrapped$ui", "(Landroidx/compose/ui/node/NodeCoordinator;)V", "wrappedBy", "getWrappedBy$ui", "setWrappedBy$ui", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "density", "", "getDensity", "()F", "fontScale", "getFontScale", "parent", "getParent", "()Landroidx/compose/ui/node/LookaheadCapablePlaceable;", "coordinates", "getCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "introducesMotionFrameOfReference", "getIntroducesMotionFrameOfReference", "released", "headNode", "includeTail", "visitNodes", "", "mask", "", "block", "Lkotlin/Function1;", "T", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "visitNodes-aLcG6gQ", "(ILkotlin/jvm/functions/Function1;)V", "hasNode", "hasNode-H91voCI", "(I)Z", "head", "head-H91voCI", "(I)Landroidx/compose/ui/Modifier$Node;", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "()J", "isClipping", "value", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "Lkotlin/ExtensionFunctionType;", "layerBlock", "getLayerBlock", "()Lkotlin/jvm/functions/Function1;", "layerDensity", "Landroidx/compose/ui/unit/Density;", "layerLayoutDirection", "lastLayerAlpha", "isTransparent", "alignmentLinesOwner", "Landroidx/compose/ui/node/AlignmentLinesOwner;", "getAlignmentLinesOwner", "()Landroidx/compose/ui/node/AlignmentLinesOwner;", "child", "getChild", "replace", "replace$ui", "hasMeasureResult", "getHasMeasureResult", "isAttached", "_measureResult", "Landroidx/compose/ui/layout/MeasureResult;", "measureResult", "getMeasureResult$ui", "()Landroidx/compose/ui/layout/MeasureResult;", "setMeasureResult$ui", "(Landroidx/compose/ui/layout/MeasureResult;)V", "Landroidx/compose/ui/node/LookaheadDelegate;", "lookaheadDelegate", "getLookaheadDelegate", "()Landroidx/compose/ui/node/LookaheadDelegate;", "setLookaheadDelegate", "(Landroidx/compose/ui/node/LookaheadDelegate;)V", "oldAlignmentLines", "Landroidx/collection/MutableObjectIntMap;", "Landroidx/compose/ui/layout/AlignmentLine;", "ensureLookaheadDelegateCreated", "providedAlignmentLines", "", "getProvidedAlignmentLines", "()Ljava/util/Set;", "onMeasureResultChanged", "width", "height", "Landroidx/compose/ui/unit/IntOffset;", "position", "getPosition-nOcc-ac", "setPosition--gyyYBs", "(J)V", "J", "zIndex", "getZIndex", "setZIndex", "(F)V", "parentData", "", "getParentData", "()Ljava/lang/Object;", "onCoordinatesUsed", "onCoordinatesUsed$ui", "parentLayoutCoordinates", "getParentLayoutCoordinates", "parentCoordinates", "getParentCoordinates", "_rectCache", "Landroidx/compose/ui/geometry/MutableRect;", "rectCache", "getRectCache", "()Landroidx/compose/ui/geometry/MutableRect;", "snapshotObserver", "Landroidx/compose/ui/node/OwnerSnapshotObserver;", "getSnapshotObserver", "()Landroidx/compose/ui/node/OwnerSnapshotObserver;", "layerPositionalProperties", "Landroidx/compose/ui/node/LayerPositionalProperties;", "lastMeasurementConstraints", "Landroidx/compose/ui/unit/Constraints;", "getLastMeasurementConstraints-msEJaDk$ui", "lastShape", "Landroidx/compose/ui/graphics/Shape;", "getLastShape$ui", "()Landroidx/compose/ui/graphics/Shape;", "setLastShape$ui", "(Landroidx/compose/ui/graphics/Shape;)V", "lastClip", "getLastClip$ui", "setLastClip$ui", "wasLayerBlockInvoked", "getWasLayerBlockInvoked$ui", "setWasLayerBlockInvoked$ui", "performingMeasure", "Landroidx/compose/ui/layout/Placeable;", "constraints", "Lkotlin/Function0;", "performingMeasure-K40F9xA", "(JLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/layout/Placeable;", "onMeasured", "onUnplaced", "placeAt", "placeAt-f8xVGno", "(JFLkotlin/jvm/functions/Function1;)V", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "(JFLandroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "placeSelf", "explicitLayer", "placeSelf-MLgxB_4", "(JFLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "releaseLayer", "placeSelfApparentToRealOffset", "placeSelfApparentToRealOffset-MLgxB_4", "draw", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "graphicsLayer", "drawContainedDrawModifiers", "performDraw", "onPlaced", "drawBlockParentLayer", "drawBlockCanvas", "_drawBlock", "Lkotlin/Function2;", "drawBlock", "getDrawBlock", "()Lkotlin/jvm/functions/Function2;", "updateLayerBlock", "forceUpdateLayerParameters", "updateLayerParameters", "invokeOnLayoutChange", "invalidateParentLayer", "lastLayerDrawingWasSkipped", "getLastLayerDrawingWasSkipped$ui", "Landroidx/compose/ui/node/OwnedLayer;", "getLayer", "()Landroidx/compose/ui/node/OwnedLayer;", "isValidOwnerScope", "minimumTouchTargetSize", "Landroidx/compose/ui/geometry/Size;", "getMinimumTouchTargetSize-NH-jbRc", "hitTest", "hitTestSource", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitTestResult", "Landroidx/compose/ui/node/HitTestResult;", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "isInLayer", "hitTest-qzLsGqo", "(Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;IZ)V", "hit", "hit-5ShdDok", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;IZ)V", "outOfBoundsHit", "distanceFromEdge", "isHitInMinimumTouchTargetBetter", "outOfBoundsHit-8NAm7pk", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;IZFZ)V", "hitNear", "hitNear-Fh5PU_I", "(Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;JLandroidx/compose/ui/node/HitTestResult;IZF)V", "speculativeHit", "speculativeHit-Fh5PU_I", "isInExpandedTouchBounds", "isInExpandedTouchBounds-ThD-n1k", "(Landroidx/compose/ui/Modifier$Node;JI)Z", "hitTestChild", "hitTestChild-qzLsGqo", "touchBoundsInRoot", "Landroidx/compose/ui/geometry/Rect;", "screenToLocal", "relativeToScreen", "screenToLocal-MK-Hz9U", "(J)J", "localToScreen", "relativeToLocal", "localToScreen-MK-Hz9U", "windowToLocal", "relativeToWindow", "windowToLocal-MK-Hz9U", "localToWindow", "localToWindow-MK-Hz9U", "toCoordinator", "localPositionOf", "sourceCoordinates", "relativeToSource", "localPositionOf-R5De75A", "(Landroidx/compose/ui/layout/LayoutCoordinates;J)J", "includeMotionFrameOfReference", "localPositionOf-S_NoaFU", "(Landroidx/compose/ui/layout/LayoutCoordinates;JZ)J", "transformFrom", "matrix", "Landroidx/compose/ui/graphics/Matrix;", "transformFrom-EL8BTi8", "(Landroidx/compose/ui/layout/LayoutCoordinates;[F)V", "transformToScreen", "transformToScreen-58bKbWc", "([F)V", "transformToAncestor", "ancestor", "transformToAncestor-EL8BTi8", "(Landroidx/compose/ui/node/NodeCoordinator;[F)V", "transformFromAncestor", "transformFromAncestor-EL8BTi8", "localBoundingBoxOf", "clipBounds", "ancestorToLocal", TypedValues.CycleType.S_WAVE_OFFSET, "ancestorToLocal-S_NoaFU", "(Landroidx/compose/ui/node/NodeCoordinator;JZ)J", "rect", "localToRoot", "localToRoot-MK-Hz9U", "withPositionTranslation", "toParentPosition", "toParentPosition-8S9VItk", "(JZ)J", "fromParentPosition", "fromParentPosition-8S9VItk", "drawBorder", "paint", "Landroidx/compose/ui/graphics/Paint;", "onLayoutNodeDetach", "onRelease", "rectInParent", "bounds", "clipToMinimumTouchTargetSize", "rectInParent$ui", "fromParentRect", "withinLayerBounds", "withinLayerBounds-k-4lQ0M", "(J)Z", "isPointerInBounds", "isPointerInBounds-k-4lQ0M", "invalidateLayer", "onLayoutModifierNodeChanged", "findCommonAncestor", "other", "findCommonAncestor$ui", "shouldSharePointerInputWithSiblings", "offsetFromEdge", "offsetFromEdge-MK-Hz9U", "calculateMinimumTouchTargetPadding", "calculateMinimumTouchTargetPadding-E7KxVPU", "calculateMinimumTouchTargetOffset", "childRect", "calculateMinimumTouchTargetOffset-C6jSQ5I", "(Landroidx/compose/ui/geometry/MutableRect;J)J", "distanceInMinimumTouchTarget", "distanceInMinimumTouchTarget-tz77jQw", "(JJ)F", "HitTestSource", "Companion", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class NodeCoordinator extends LookaheadCapablePlaceable implements Measurable, LayoutCoordinates, OwnerScope {
    public static final int $stable = 0;
    public static final String ExpectAttachedLayoutCoordinates = "LayoutCoordinate operations are only valid when isAttached is true";
    public static final String UnmeasuredError = "Asking for measurement result of unmeasured layout modifier";
    private Function2<? super Canvas, ? super GraphicsLayer, Unit> _drawBlock;
    private MeasureResult _measureResult;
    private MutableRect _rectCache;
    private Canvas drawBlockCanvas;
    private GraphicsLayer drawBlockParentLayer;
    private GraphicsLayer explicitLayer;
    private boolean forceMeasureWithLookaheadConstraints;
    private boolean forcePlaceWithLookaheadOffset;
    private boolean isClipping;
    private boolean lastClip;
    private boolean lastLayerDrawingWasSkipped;
    private OwnedLayer layer;
    private Function1<? super GraphicsLayerScope, Unit> layerBlock;
    private LayerPositionalProperties layerPositionalProperties;
    private final LayoutNode layoutNode;
    private MutableObjectIntMap<AlignmentLine> oldAlignmentLines;
    private boolean released;
    private boolean wasLayerBlockInvoked;
    private NodeCoordinator wrapped;
    private NodeCoordinator wrappedBy;
    private float zIndex;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Function1<NodeCoordinator, Unit> onCommitAffectingLayerParams = new Function1<NodeCoordinator, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayerParams$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(NodeCoordinator nodeCoordinator) throws Throwable {
            invoke2(nodeCoordinator);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(NodeCoordinator coordinator) throws Throwable {
            LayoutNode layoutNode$iv = coordinator.getLayoutNode();
            try {
                if (coordinator.isValidOwnerScope()) {
                    NodeCoordinator.updateLayerParameters$default(coordinator, false, 1, null);
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable e$iv) {
                layoutNode$iv.rethrowWithComposeStackTrace(e$iv);
                throw new KotlinNothingValueException();
            }
        }
    };
    private static final Function1<NodeCoordinator, Unit> onCommitAffectingLayer = new Function1<NodeCoordinator, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayer$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(NodeCoordinator nodeCoordinator) {
            invoke2(nodeCoordinator);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(NodeCoordinator coordinator) {
            OwnedLayer layer = coordinator.getLayer();
            if (layer != null) {
                layer.invalidate();
            }
        }
    };
    private static final ReusableGraphicsLayerScope graphicsLayerScope = new ReusableGraphicsLayerScope();
    private static final LayerPositionalProperties tmpLayerPositionalProperties = new LayerPositionalProperties();
    private static final float[] tmpMatrix = Matrix.m5557constructorimpl$default(null, 1, null);
    private static final HitTestSource PointerInputSource = new HitTestSource() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$PointerInputSource$1
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: entityType-OLwlOKw, reason: not valid java name */
        public int mo7096entityTypeOLwlOKw() {
            return NodeKind.m7100constructorimpl(16);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean interceptOutOfBoundsChildEvents(Modifier.Node node) {
            boolean dispatchAgain$iv$iv;
            int kind$iv;
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv;
            int kind$iv2;
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv2;
            MutableVector mutableVector;
            int kind$iv3 = NodeKind.m7100constructorimpl(16);
            Modifier.Node $this$dispatchForKind_u2d6rFNWt0$iv3 = node;
            MutableVector mutableVector2 = null;
            Modifier.Node nodePop = $this$dispatchForKind_u2d6rFNWt0$iv3;
            while (nodePop != null) {
                int i = 1;
                if (nodePop instanceof PointerInputModifierNode) {
                    PointerInputModifierNode it = (PointerInputModifierNode) nodePop;
                    if (it.interceptOutOfBoundsChildEvents()) {
                        return true;
                    }
                    dispatchAgain$iv$iv = false;
                } else {
                    dispatchAgain$iv$iv = true;
                }
                if (dispatchAgain$iv$iv) {
                    Modifier.Node this_$iv$iv$iv = nodePop;
                    int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & kind$iv3) != 0 ? 1 : 0;
                    if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                        int count$iv$iv = 0;
                        DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                        Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                        while (node$iv$iv$iv != null) {
                            Modifier.Node next$iv$iv = node$iv$iv$iv;
                            int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & kind$iv3) != 0 ? i : 0;
                            if (kind$iv$iv$iv2 == 0) {
                                kind$iv2 = kind$iv3;
                                $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                            } else {
                                count$iv$iv++;
                                if (count$iv$iv == i) {
                                    nodePop = next$iv$iv;
                                    kind$iv2 = kind$iv3;
                                    $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                } else {
                                    if (mutableVector2 != null) {
                                        kind$iv2 = kind$iv3;
                                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                        mutableVector = mutableVector2;
                                    } else {
                                        kind$iv2 = kind$iv3;
                                        $this$dispatchForKind_u2d6rFNWt0$iv2 = $this$dispatchForKind_u2d6rFNWt0$iv3;
                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                    }
                                    mutableVector2 = mutableVector;
                                    Modifier.Node theNode$iv$iv = nodePop;
                                    if (theNode$iv$iv != null) {
                                        if (mutableVector2 != null) {
                                            mutableVector2.add(theNode$iv$iv);
                                        }
                                        nodePop = null;
                                    }
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(next$iv$iv);
                                    }
                                }
                            }
                            node$iv$iv$iv = node$iv$iv$iv.getChild();
                            kind$iv3 = kind$iv2;
                            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv2;
                            i = 1;
                        }
                        kind$iv = kind$iv3;
                        $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                        if (count$iv$iv != 1) {
                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                            kind$iv3 = kind$iv;
                            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        } else {
                            kind$iv3 = kind$iv;
                            $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
                        }
                    }
                }
                kind$iv = kind$iv3;
                $this$dispatchForKind_u2d6rFNWt0$iv = $this$dispatchForKind_u2d6rFNWt0$iv3;
                nodePop = DelegatableNodeKt.pop(mutableVector2);
                kind$iv3 = kind$iv;
                $this$dispatchForKind_u2d6rFNWt0$iv3 = $this$dispatchForKind_u2d6rFNWt0$iv;
            }
            return false;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTestChildren(LayoutNode parentLayoutNode) {
            return true;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: childHitTest-qzLsGqo, reason: not valid java name */
        public void mo7095childHitTestqzLsGqo(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
            layoutNode.m7010hitTest6fMxITs$ui(pointerPosition, hitTestResult, pointerType, isInLayer);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shareWithSiblings(HitTestResult hitTestResult, LayoutNode child) {
            if (child.getOuterCoordinator$ui().shouldSharePointerInputWithSiblings()) {
                hitTestResult.acceptHits();
                return true;
            }
            return false;
        }
    };
    private static final HitTestSource SemanticsSource = new HitTestSource() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$SemanticsSource$1
        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: entityType-OLwlOKw */
        public int mo7096entityTypeOLwlOKw() {
            return NodeKind.m7100constructorimpl(8);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean interceptOutOfBoundsChildEvents(Modifier.Node node) {
            return false;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTestChildren(LayoutNode parentLayoutNode) {
            SemanticsConfiguration semanticsConfiguration = parentLayoutNode.getSemanticsConfiguration();
            boolean z = false;
            if (semanticsConfiguration != null && semanticsConfiguration.getIsClearingSemantics()) {
                z = true;
            }
            return !z;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        /* JADX INFO: renamed from: childHitTest-qzLsGqo */
        public void mo7095childHitTestqzLsGqo(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
            layoutNode.m7011hitTestSemantics6fMxITs$ui(pointerPosition, hitTestResult, pointerType, isInLayer);
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shouldHitTest(Modifier.Node node) {
            if (ComposeUiFlags.isSkipNonImportantSemanticsNodesHitTestEnabled) {
                return SemanticsOwnerKt.isImportantForAccessibility(SemanticsNodeKt.SemanticsNode(DelegatableNodeKt.requireLayoutNode(node), false));
            }
            return true;
        }

        @Override // androidx.compose.ui.node.NodeCoordinator.HitTestSource
        public boolean shareWithSiblings(HitTestResult hitTestResult, LayoutNode child) {
            return false;
        }
    };
    private Density layerDensity = getLayoutNode().getDensity();
    private LayoutDirection layerLayoutDirection = getLayoutNode().getLayoutDirection();
    private float lastLayerAlpha = 0.8f;
    private long position = IntOffset.INSTANCE.m8289getZeronOccac();
    private Shape lastShape = RectangleShapeKt.getRectangleShape();
    private final Function0<Unit> invalidateParentLayer = new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$invalidateParentLayer$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            NodeCoordinator wrappedBy = this.this$0.getWrappedBy();
            if (wrappedBy != null) {
                wrappedBy.invalidateLayer();
            }
        }
    };

    public abstract void ensureLookaheadDelegateCreated();

    public abstract LookaheadDelegate getLookaheadDelegate();

    public abstract Modifier.Node getTail();

    protected abstract void setLookaheadDelegate(LookaheadDelegate lookaheadDelegate);

    public NodeCoordinator(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    /* JADX INFO: renamed from: getForcePlaceWithLookaheadOffset$ui, reason: from getter */
    public final boolean getForcePlaceWithLookaheadOffset() {
        return this.forcePlaceWithLookaheadOffset;
    }

    public final void setForcePlaceWithLookaheadOffset$ui(boolean z) {
        this.forcePlaceWithLookaheadOffset = z;
    }

    /* JADX INFO: renamed from: getForceMeasureWithLookaheadConstraints$ui, reason: from getter */
    public final boolean getForceMeasureWithLookaheadConstraints() {
        return this.forceMeasureWithLookaheadConstraints;
    }

    public final void setForceMeasureWithLookaheadConstraints$ui(boolean z) {
        this.forceMeasureWithLookaheadConstraints = z;
    }

    /* JADX INFO: renamed from: getWrapped$ui, reason: from getter */
    public final NodeCoordinator getWrapped() {
        return this.wrapped;
    }

    public final void setWrapped$ui(NodeCoordinator nodeCoordinator) {
        this.wrapped = nodeCoordinator;
    }

    /* JADX INFO: renamed from: getWrappedBy$ui, reason: from getter */
    public final NodeCoordinator getWrappedBy() {
        return this.wrappedBy;
    }

    public final void setWrappedBy$ui(NodeCoordinator nodeCoordinator) {
        this.wrappedBy = nodeCoordinator;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public LayoutDirection getLayoutDirection() {
        return getLayoutNode().getLayoutDirection();
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: getDensity */
    public float get_density() {
        return getLayoutNode().getDensity().get_density();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: getFontScale */
    public float get_fontScale() {
        return getLayoutNode().getDensity().get_fontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getParent() {
        return this.wrappedBy;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LayoutCoordinates getCoordinates() {
        return this;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean getIntroducesMotionFrameOfReference() {
        return getIsPlacedUnderMotionFrameOfReference();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modifier.Node headNode(boolean includeTail) {
        Modifier.Node tail;
        if (getLayoutNode().getOuterCoordinator$ui() == this) {
            return getLayoutNode().getNodes().getHead();
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (includeTail) {
            if (nodeCoordinator == null || (tail = nodeCoordinator.getTail()) == null) {
                return null;
            }
            return tail.getChild();
        }
        if (nodeCoordinator != null) {
            return nodeCoordinator.getTail();
        }
        return null;
    }

    public final void visitNodes(int mask, boolean includeTail, Function1<? super Modifier.Node, Unit> block) {
        Modifier.Node stopNode = getTail();
        if (!includeTail && (stopNode = stopNode.getParent()) == null) {
            return;
        }
        for (Modifier.Node node = headNode(includeTail); node != null && (node.getAggregateChildKindSet() & mask) != 0; node = node.getChild()) {
            if ((node.getKindSet() & mask) != 0) {
                block.invoke(node);
            }
            if (node == stopNode) {
                return;
            }
        }
    }

    /* JADX INFO: renamed from: visitNodes-aLcG6gQ, reason: not valid java name */
    public final /* synthetic */ <T> void m7093visitNodesaLcG6gQ(int type, Function1<? super T, Unit> block) {
        int i;
        boolean includeTail$iv;
        NodeCoordinator this_$iv;
        int mask$iv;
        boolean dispatchAgain$iv$iv;
        int mask$iv2;
        int mask$iv3;
        Object node$iv$iv;
        int count$iv$iv;
        Object mutableVector;
        Object node$iv$iv2;
        int i2 = 0;
        boolean includeTail$iv2 = NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type);
        NodeCoordinator this_$iv2 = this;
        int mask$iv4 = type;
        Modifier.Node stopNode$iv = this_$iv2.getTail();
        if (includeTail$iv2 || (stopNode$iv = stopNode$iv.getParent()) != null) {
            Modifier.Node node$iv = this_$iv2.headNode(includeTail$iv2);
            while (node$iv != null && (node$iv.getAggregateChildKindSet() & mask$iv4) != 0) {
                if ((node$iv.getKindSet() & mask$iv4) == 0) {
                    i = i2;
                    includeTail$iv = includeTail$iv2;
                    this_$iv = this_$iv2;
                    mask$iv = mask$iv4;
                } else {
                    Object it = node$iv;
                    Object stack$iv$iv = null;
                    i = i2;
                    Object node$iv$iv3 = it;
                    while (node$iv$iv3 != null) {
                        boolean includeTail$iv3 = includeTail$iv2;
                        NodeCoordinator this_$iv3 = this_$iv2;
                        Intrinsics.reifiedOperationMarker(3, "T");
                        if (node$iv$iv3 instanceof Object) {
                            block.invoke(node$iv$iv3);
                            dispatchAgain$iv$iv = false;
                        } else {
                            dispatchAgain$iv$iv = true;
                        }
                        if (dispatchAgain$iv$iv) {
                            Modifier.Node this_$iv$iv$iv = (Modifier.Node) node$iv$iv3;
                            if (((this_$iv$iv$iv.getKindSet() & type) != 0) && (node$iv$iv3 instanceof DelegatingNode)) {
                                int count$iv$iv2 = 0;
                                DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) node$iv$iv3;
                                Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv != null) {
                                    Object node$iv$iv4 = node$iv$iv3;
                                    Object node$iv$iv5 = node$iv$iv$iv;
                                    Modifier.Node next$iv$iv = (Modifier.Node) node$iv$iv5;
                                    int kind$iv$iv$iv = (next$iv$iv.getKindSet() & type) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv == 0) {
                                        mask$iv3 = mask$iv4;
                                        node$iv$iv = node$iv$iv4;
                                    } else {
                                        count$iv$iv2++;
                                        if (count$iv$iv2 == 1) {
                                            node$iv$iv = next$iv$iv;
                                            mask$iv3 = mask$iv4;
                                        } else {
                                            Object node$iv$iv6 = stack$iv$iv;
                                            Object obj = (MutableVector) node$iv$iv6;
                                            if (obj != null) {
                                                count$iv$iv = count$iv$iv2;
                                                mask$iv3 = mask$iv4;
                                                mutableVector = obj;
                                            } else {
                                                count$iv$iv = count$iv$iv2;
                                                mask$iv3 = mask$iv4;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            stack$iv$iv = mutableVector;
                                            Modifier.Node theNode$iv$iv = (Modifier.Node) node$iv$iv4;
                                            if (theNode$iv$iv == null) {
                                                node$iv$iv2 = node$iv$iv4;
                                            } else {
                                                MutableVector mutableVector2 = (MutableVector) stack$iv$iv;
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(theNode$iv$iv);
                                                }
                                                node$iv$iv2 = null;
                                            }
                                            MutableVector mutableVector3 = (MutableVector) stack$iv$iv;
                                            if (mutableVector3 != null) {
                                                mutableVector3.add(next$iv$iv);
                                            }
                                            node$iv$iv = node$iv$iv2;
                                            count$iv$iv2 = count$iv$iv;
                                        }
                                    }
                                    node$iv$iv$iv = node$iv$iv$iv.getChild();
                                    node$iv$iv3 = node$iv$iv;
                                    mask$iv4 = mask$iv3;
                                }
                                Object node$iv$iv7 = node$iv$iv3;
                                mask$iv2 = mask$iv4;
                                if (count$iv$iv2 != 1) {
                                    node$iv$iv3 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv);
                                    includeTail$iv2 = includeTail$iv3;
                                    this_$iv2 = this_$iv3;
                                    mask$iv4 = mask$iv2;
                                } else {
                                    includeTail$iv2 = includeTail$iv3;
                                    this_$iv2 = this_$iv3;
                                    node$iv$iv3 = node$iv$iv7;
                                    mask$iv4 = mask$iv2;
                                }
                            }
                        }
                        mask$iv2 = mask$iv4;
                        node$iv$iv3 = DelegatableNodeKt.pop((MutableVector) stack$iv$iv);
                        includeTail$iv2 = includeTail$iv3;
                        this_$iv2 = this_$iv3;
                        mask$iv4 = mask$iv2;
                    }
                    includeTail$iv = includeTail$iv2;
                    this_$iv = this_$iv2;
                    mask$iv = mask$iv4;
                }
                if (node$iv == stopNode$iv) {
                    return;
                }
                node$iv = node$iv.getChild();
                i2 = i;
                includeTail$iv2 = includeTail$iv;
                this_$iv2 = this_$iv;
                mask$iv4 = mask$iv;
            }
        }
    }

    /* JADX INFO: renamed from: hasNode-H91voCI, reason: not valid java name */
    private final boolean m7069hasNodeH91voCI(int type) {
        Modifier.Node nodeHeadNode = headNode(NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type));
        return nodeHeadNode != null && DelegatableNodeKt.m6953has64DMado(nodeHeadNode, type);
    }

    /* JADX INFO: renamed from: head-H91voCI, reason: not valid java name */
    public final Modifier.Node m7086headH91voCI(int type) {
        boolean includeTail$iv = NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type);
        Modifier.Node stopNode$iv = getTail();
        if (includeTail$iv || (stopNode$iv = stopNode$iv.getParent()) != null) {
            for (Modifier.Node node$iv = headNode(includeTail$iv); node$iv != null && (node$iv.getAggregateChildKindSet() & type) != 0; node$iv = node$iv.getChild()) {
                if ((node$iv.getKindSet() & type) != 0) {
                    Modifier.Node it = node$iv;
                    return it;
                }
                if (node$iv == stopNode$iv) {
                    return null;
                }
            }
            return null;
        }
        return null;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: getSize-YbymL2g */
    public final long mo6791getSizeYbymL2g() {
        return getMeasuredSize();
    }

    protected final Function1<GraphicsLayerScope, Unit> getLayerBlock() {
        return this.layerBlock;
    }

    public final boolean isTransparent() {
        if (this.layer != null && this.lastLayerAlpha <= 0.0f) {
            return true;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            return nodeCoordinator.isTransparent();
        }
        return false;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public AlignmentLinesOwner getAlignmentLinesOwner() {
        return getLayoutNode().getLayoutDelegate().getAlignmentLinesOwner$ui();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public LookaheadCapablePlaceable getChild() {
        return this.wrapped;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public void replace$ui() {
        GraphicsLayer explicitLayer = this.explicitLayer;
        if (explicitLayer != null) {
            mo6846placeAtf8xVGno(getPosition(), this.zIndex, explicitLayer);
        } else {
            mo6784placeAtf8xVGno(getPosition(), this.zIndex, this.layerBlock);
        }
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public boolean isAttached() {
        return getTail().getIsAttached();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public MeasureResult getMeasureResult$ui() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        throw new IllegalStateException(UnmeasuredError.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setMeasureResult$ui(androidx.compose.ui.layout.MeasureResult r11) {
        /*
            r10 = this;
            androidx.compose.ui.layout.MeasureResult r0 = r10._measureResult
            if (r11 == r0) goto L9a
            r10._measureResult = r11
            if (r0 == 0) goto L1c
            int r1 = r11.get$w()
            int r2 = r0.get$w()
            if (r1 != r2) goto L1c
            int r1 = r11.get$h()
            int r2 = r0.get$h()
            if (r1 == r2) goto L27
        L1c:
            int r1 = r11.get$w()
            int r2 = r11.get$h()
            r10.onMeasureResultChanged(r1, r2)
        L27:
            androidx.collection.MutableObjectIntMap<androidx.compose.ui.layout.AlignmentLine> r1 = r10.oldAlignmentLines
            if (r1 == 0) goto L37
            androidx.collection.MutableObjectIntMap<androidx.compose.ui.layout.AlignmentLine> r1 = r10.oldAlignmentLines
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            boolean r1 = r1.isNotEmpty()
            if (r1 != 0) goto L41
        L37:
            java.util.Map r1 = r11.getAlignmentLines()
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L9a
        L41:
            androidx.collection.MutableObjectIntMap<androidx.compose.ui.layout.AlignmentLine> r1 = r10.oldAlignmentLines
            java.util.Map r2 = r11.getAlignmentLines()
            boolean r1 = androidx.compose.ui.node.NodeCoordinatorKt.access$compareEquals(r1, r2)
            if (r1 != 0) goto L9a
            androidx.compose.ui.node.AlignmentLinesOwner r1 = r10.getAlignmentLinesOwner()
            androidx.compose.ui.node.AlignmentLines r1 = r1.getAlignmentLines()
            r1.onAlignmentsChanged()
            androidx.collection.MutableObjectIntMap<androidx.compose.ui.layout.AlignmentLine> r1 = r10.oldAlignmentLines
            if (r1 != 0) goto L66
            androidx.collection.MutableObjectIntMap r1 = androidx.collection.ObjectIntMapKt.mutableObjectIntMapOf()
            r2 = r1
            r3 = 0
            r10.oldAlignmentLines = r2
        L66:
            r1.clear()
            java.util.Map r2 = r11.getAlignmentLines()
            r3 = 0
            java.util.Set r4 = r2.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L78:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L99
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            r6 = r5
            r7 = 0
            java.lang.Object r8 = r6.getKey()
            java.lang.Object r9 = r6.getValue()
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            r1.set(r8, r9)
            goto L78
        L99:
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinator.setMeasureResult$ui(androidx.compose.ui.layout.MeasureResult):void");
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Set<AlignmentLine> getProvidedAlignmentLines() {
        Set setAsSet;
        MutableScatterSet set = null;
        for (NodeCoordinator coordinator = this; coordinator != null; coordinator = coordinator.wrapped) {
            MeasureResult measureResult = coordinator._measureResult;
            Map<AlignmentLine, Integer> alignmentLines = measureResult != null ? measureResult.getAlignmentLines() : null;
            boolean z = false;
            if (alignmentLines != null && (!alignmentLines.isEmpty())) {
                z = true;
            }
            if (z) {
                if (set == null) {
                    set = ScatterSetKt.mutableScatterSetOf();
                }
                set.addAll(alignmentLines.keySet());
            }
        }
        return (set == null || (setAsSet = set.asSet()) == null) ? SetsKt.emptySet() : setAsSet;
    }

    protected void onMeasureResultChanged(int width, int height) {
        NodeCoordinator nodeCoordinator;
        OwnedLayer layer;
        int type$iv;
        NodeCoordinator this_$iv;
        boolean dispatchAgain$iv$iv$iv;
        int type$iv2;
        NodeCoordinator this_$iv2;
        int type$iv3;
        NodeCoordinator this_$iv3;
        int count$iv$iv$iv;
        Modifier.Node node;
        MutableVector mutableVector;
        OwnedLayer layer2 = this.layer;
        if (layer2 != null) {
            layer2.mo7162resizeozmzZPI(IntSize.m8316constructorimpl((((long) width) << 32) | (((long) height) & 4294967295L)));
        } else if (getLayoutNode().isPlaced() && (nodeCoordinator = this.wrappedBy) != null) {
            nodeCoordinator.invalidateLayer();
        }
        m6847setMeasuredSizeozmzZPI(IntSize.m8316constructorimpl((4294967295L & ((long) height)) | (((long) width) << 32)));
        if (this.layerBlock != null) {
            updateLayerParameters(false);
        }
        int type$iv4 = NodeKind.m7100constructorimpl(4);
        NodeCoordinator this_$iv4 = this;
        boolean includeTail$iv$iv = NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type$iv4);
        Modifier.Node stopNode$iv$iv = this_$iv4.getTail();
        if (includeTail$iv$iv || (stopNode$iv$iv = stopNode$iv$iv.getParent()) != null) {
            Modifier.Node node$iv$iv = this_$iv4.headNode(includeTail$iv$iv);
            while (node$iv$iv != null && (node$iv$iv.getAggregateChildKindSet() & type$iv4) != 0) {
                if ((node$iv$iv.getKindSet() & type$iv4) != 0) {
                    Modifier.Node it$iv = node$iv$iv;
                    int kind$iv$iv = type$iv4;
                    MutableVector mutableVector2 = null;
                    Modifier.Node nodePop = it$iv;
                    while (nodePop != null) {
                        OwnedLayer layer3 = layer2;
                        if (nodePop instanceof DrawModifierNode) {
                            DrawModifierNode it = (DrawModifierNode) nodePop;
                            it.onMeasureResultChanged();
                            dispatchAgain$iv$iv$iv = false;
                        } else {
                            dispatchAgain$iv$iv$iv = true;
                        }
                        if (dispatchAgain$iv$iv$iv) {
                            Modifier.Node this_$iv$iv$iv$iv = nodePop;
                            int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                            if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                int count$iv$iv$iv2 = 0;
                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv$iv != null) {
                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                    int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv2 == 0) {
                                        type$iv3 = type$iv4;
                                        this_$iv3 = this_$iv4;
                                    } else {
                                        count$iv$iv$iv2++;
                                        type$iv3 = type$iv4;
                                        if (count$iv$iv$iv2 == 1) {
                                            nodePop = next$iv$iv$iv;
                                            this_$iv3 = this_$iv4;
                                        } else {
                                            if (mutableVector2 != null) {
                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                node = nodePop;
                                                this_$iv3 = this_$iv4;
                                                mutableVector = mutableVector2;
                                            } else {
                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                node = nodePop;
                                                this_$iv3 = this_$iv4;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            Modifier.Node theNode$iv$iv$iv = node;
                                            if (theNode$iv$iv$iv == null) {
                                                nodePop = node;
                                            } else {
                                                if (mutableVector != null) {
                                                    mutableVector.add(theNode$iv$iv$iv);
                                                }
                                                nodePop = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(next$iv$iv$iv);
                                            }
                                            mutableVector2 = mutableVector;
                                            count$iv$iv$iv2 = count$iv$iv$iv;
                                        }
                                    }
                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                    type$iv4 = type$iv3;
                                    this_$iv4 = this_$iv3;
                                }
                                type$iv2 = type$iv4;
                                Modifier.Node node2 = nodePop;
                                this_$iv2 = this_$iv4;
                                if (count$iv$iv$iv2 != 1) {
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    layer2 = layer3;
                                    type$iv4 = type$iv2;
                                    this_$iv4 = this_$iv2;
                                } else {
                                    layer2 = layer3;
                                    type$iv4 = type$iv2;
                                    nodePop = node2;
                                    this_$iv4 = this_$iv2;
                                }
                            }
                        }
                        type$iv2 = type$iv4;
                        this_$iv2 = this_$iv4;
                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                        layer2 = layer3;
                        type$iv4 = type$iv2;
                        this_$iv4 = this_$iv2;
                    }
                    layer = layer2;
                    type$iv = type$iv4;
                    this_$iv = this_$iv4;
                } else {
                    layer = layer2;
                    type$iv = type$iv4;
                    this_$iv = this_$iv4;
                }
                if (node$iv$iv == stopNode$iv$iv) {
                    break;
                }
                node$iv$iv = node$iv$iv.getChild();
                layer2 = layer;
                type$iv4 = type$iv;
                this_$iv4 = this_$iv;
            }
        }
        Owner owner = getLayoutNode().getOwner();
        if (owner != null) {
            owner.onLayoutChange(getLayoutNode());
        }
        getLayoutNode().onCoordinatorRectChanged$ui(this);
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* JADX INFO: renamed from: getPosition-nOcc-ac, reason: from getter */
    public long getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: setPosition--gyyYBs, reason: not valid java name */
    protected void m7091setPositiongyyYBs(long j) {
        this.position = j;
    }

    public final float getZIndex() {
        return this.zIndex;
    }

    protected final void setZIndex(float f) {
        this.zIndex = f;
    }

    /* JADX WARN: Type inference failed for: r3v20, types: [T, java.lang.Object] */
    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public Object getParentData() {
        NodeChain this_$iv;
        int $i$f$tailToHead$ui;
        NodeChain this_$iv2;
        int $i$f$tailToHead$ui2;
        boolean dispatchAgain$iv$iv;
        boolean dispatchAgain$iv$iv2;
        MutableVector mutableVector;
        Modifier.Node node;
        int count$iv$iv;
        int i = 64;
        if (!getLayoutNode().getNodes().m7060hasH91voCI$ui(NodeKind.m7100constructorimpl(64))) {
            return null;
        }
        Modifier.Node thisNode = getTail();
        Ref.ObjectRef data = new Ref.ObjectRef();
        NodeChain this_$iv3 = getLayoutNode().getNodes();
        int count$iv$iv2 = 0;
        Modifier.Node node$iv = this_$iv3.getTail();
        while (node$iv != null) {
            Modifier.Node node2 = node$iv;
            int kind$iv = (node2.getKindSet() & NodeKind.m7100constructorimpl(i)) != 0 ? 1 : 0;
            if (kind$iv != 0) {
                int iM7100constructorimpl = NodeKind.m7100constructorimpl(i);
                MutableVector mutableVector2 = null;
                Modifier.Node nodePop = node2;
                while (nodePop != null) {
                    if (nodePop instanceof ParentDataModifierNode) {
                        ParentDataModifierNode it = (ParentDataModifierNode) nodePop;
                        this_$iv2 = this_$iv3;
                        $i$f$tailToHead$ui2 = count$iv$iv2;
                        data.element = it.modifyParentData(getLayoutNode().getDensity(), data.element);
                        dispatchAgain$iv$iv = false;
                    } else {
                        this_$iv2 = this_$iv3;
                        $i$f$tailToHead$ui2 = count$iv$iv2;
                        dispatchAgain$iv$iv = true;
                    }
                    if (dispatchAgain$iv$iv) {
                        Modifier.Node this_$iv$iv$iv = nodePop;
                        int kind$iv$iv$iv = (this_$iv$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                        if (kind$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                            int count$iv$iv3 = 0;
                            DelegatingNode this_$iv$iv$iv2 = (DelegatingNode) nodePop;
                            Modifier.Node node$iv$iv$iv = this_$iv$iv$iv2.getDelegate();
                            while (node$iv$iv$iv != null) {
                                Modifier.Node next$iv$iv = node$iv$iv$iv;
                                int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & iM7100constructorimpl) != 0 ? 1 : 0;
                                if (kind$iv$iv$iv2 != 0) {
                                    count$iv$iv3++;
                                    Modifier.Node node3 = nodePop;
                                    if (count$iv$iv3 == 1) {
                                        dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                        mutableVector = mutableVector2;
                                        node = next$iv$iv;
                                    } else {
                                        if (mutableVector2 == null) {
                                            dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                            count$iv$iv = count$iv$iv3;
                                            mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                        } else {
                                            dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                            count$iv$iv = count$iv$iv3;
                                            mutableVector = mutableVector2;
                                        }
                                        if (node3 != null) {
                                            if (mutableVector != null) {
                                                mutableVector.add(node3);
                                            }
                                            node = null;
                                        } else {
                                            node = node3;
                                        }
                                        if (mutableVector != null) {
                                            mutableVector.add(next$iv$iv);
                                        }
                                        count$iv$iv3 = count$iv$iv;
                                    }
                                } else {
                                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                                    mutableVector = mutableVector2;
                                    node = nodePop;
                                }
                                node$iv$iv$iv = node$iv$iv$iv.getChild();
                                nodePop = node;
                                mutableVector2 = mutableVector;
                                dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                            }
                            Modifier.Node node4 = nodePop;
                            if (count$iv$iv3 == 1) {
                                this_$iv3 = this_$iv2;
                                count$iv$iv2 = $i$f$tailToHead$ui2;
                                nodePop = node4;
                            } else {
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                this_$iv3 = this_$iv2;
                                count$iv$iv2 = $i$f$tailToHead$ui2;
                            }
                        }
                    }
                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                    this_$iv3 = this_$iv2;
                    count$iv$iv2 = $i$f$tailToHead$ui2;
                }
                this_$iv = this_$iv3;
                $i$f$tailToHead$ui = count$iv$iv2;
            } else {
                this_$iv = this_$iv3;
                $i$f$tailToHead$ui = count$iv$iv2;
            }
            if (node2 != thisNode) {
            }
            node$iv = node$iv.getParent();
            this_$iv3 = this_$iv;
            count$iv$iv2 = $i$f$tailToHead$ui;
            i = 64;
        }
        return data.element;
    }

    public final void onCoordinatesUsed$ui() {
        getLayoutNode().getLayoutDelegate().onCoordinatesUsed();
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        boolean value$iv = isAttached();
        if (!value$iv) {
            StringBuilder builder = new StringBuilder(ExpectAttachedLayoutCoordinates);
            for (LayoutNode node = getLayoutNode(); node != null; node = node.getParent$ui()) {
                builder.append('\n');
                builder.append("|");
                builder.append(node);
                builder.append(" isAttached=");
                builder.append(node.isAttached());
                builder.append(" modifier=");
                builder.append(node.get_modifier());
                builder.append(" tail=");
                builder.append(getTail());
            }
            InlineClassHelperKt.throwIllegalStateException(builder.toString());
        }
        onCoordinatesUsed$ui();
        return getLayoutNode().getOuterCoordinator$ui().wrappedBy;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentCoordinates() {
        boolean value$iv = isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui();
        return this.wrappedBy;
    }

    protected final MutableRect getRectCache() {
        MutableRect mutableRect = this._rectCache;
        if (mutableRect != null) {
            return mutableRect;
        }
        MutableRect it = new MutableRect(0.0f, 0.0f, 0.0f, 0.0f);
        this._rectCache = it;
        return it;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final OwnerSnapshotObserver getSnapshotObserver() {
        return LayoutNodeKt.requireOwner(getLayoutNode()).getSnapshotObserver();
    }

    /* JADX INFO: renamed from: getLastMeasurementConstraints-msEJaDk$ui, reason: not valid java name */
    public final long m7084getLastMeasurementConstraintsmsEJaDk$ui() {
        return getMeasurementConstraints();
    }

    /* JADX INFO: renamed from: getLastShape$ui, reason: from getter */
    public final Shape getLastShape() {
        return this.lastShape;
    }

    public final void setLastShape$ui(Shape shape) {
        this.lastShape = shape;
    }

    /* JADX INFO: renamed from: getLastClip$ui, reason: from getter */
    public final boolean getLastClip() {
        return this.lastClip;
    }

    public final void setLastClip$ui(boolean z) {
        this.lastClip = z;
    }

    /* JADX INFO: renamed from: getWasLayerBlockInvoked$ui, reason: from getter */
    public final boolean getWasLayerBlockInvoked() {
        return this.wasLayerBlockInvoked;
    }

    public final void setWasLayerBlockInvoked$ui(boolean z) {
        this.wasLayerBlockInvoked = z;
    }

    /* JADX INFO: renamed from: performingMeasure-K40F9xA, reason: not valid java name */
    protected final Placeable m7089performingMeasureK40F9xA(long constraints, Function0<? extends Placeable> block) {
        m6848setMeasurementConstraintsBRTryo0(constraints);
        return block.invoke();
    }

    public final void onMeasured() throws Throwable {
        Modifier.Node stopNode$iv$iv;
        int i;
        int type$iv;
        int $i$f$withoutReadObservation;
        int $i$f$withoutReadObservation2;
        boolean dispatchAgain$iv$iv$iv;
        Modifier.Node node;
        boolean dispatchAgain$iv$iv$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        if (m7069hasNodeH91voCI(NodeKind.m7100constructorimpl(128))) {
            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
            int $i$f$withoutReadObservation3 = 0;
            Snapshot previousSnapshot$iv = this_$iv.getCurrentThreadSnapshot();
            Function1<Object, Unit> readObserver = previousSnapshot$iv != null ? previousSnapshot$iv.getReadObserver() : null;
            Snapshot newSnapshot$iv = this_$iv.makeCurrentNonObservable(previousSnapshot$iv);
            int i2 = 0;
            try {
                int type$iv2 = NodeKind.m7100constructorimpl(128);
                boolean includeTail$iv$iv = NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type$iv2);
                if (!includeTail$iv$iv) {
                    stopNode$iv$iv = getTail().getParent();
                    if (stopNode$iv$iv == null) {
                    }
                    Unit unit = Unit.INSTANCE;
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                }
                try {
                    stopNode$iv$iv = getTail();
                } catch (Throwable th) {
                    th = th;
                    this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                    throw th;
                }
                Modifier.Node node$iv$iv = headNode(includeTail$iv$iv);
                while (node$iv$iv != null) {
                    if ((node$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                        if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                            Modifier.Node it$iv = node$iv$iv;
                            int kind$iv$iv = type$iv2;
                            MutableVector mutableVector2 = null;
                            i = i2;
                            Modifier.Node nodePop = it$iv;
                            while (nodePop != null) {
                                int type$iv3 = type$iv2;
                                if (nodePop instanceof MeasuredSizeAwareModifierNode) {
                                    MeasuredSizeAwareModifierNode it = (MeasuredSizeAwareModifierNode) nodePop;
                                    $i$f$withoutReadObservation2 = $i$f$withoutReadObservation3;
                                    try {
                                        it.mo421onRemeasuredozmzZPI(getMeasuredSize());
                                        dispatchAgain$iv$iv$iv = false;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
                                        throw th;
                                    }
                                } else {
                                    $i$f$withoutReadObservation2 = $i$f$withoutReadObservation3;
                                    dispatchAgain$iv$iv$iv = true;
                                }
                                if (dispatchAgain$iv$iv$iv) {
                                    Modifier.Node this_$iv$iv$iv$iv = nodePop;
                                    int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                        int count$iv$iv$iv2 = 0;
                                        DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                        Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                        while (node$iv$iv$iv$iv != null) {
                                            Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                            int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                            if (kind$iv$iv$iv$iv2 == 0) {
                                                node = nodePop;
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                            } else {
                                                count$iv$iv$iv2++;
                                                node = nodePop;
                                                if (count$iv$iv$iv2 == 1) {
                                                    node = next$iv$iv$iv;
                                                    dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                } else {
                                                    if (mutableVector2 != null) {
                                                        dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        mutableVector = mutableVector2;
                                                    } else {
                                                        dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                        count$iv$iv$iv = count$iv$iv$iv2;
                                                        mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                                    }
                                                    if (node != null) {
                                                        if (mutableVector != null) {
                                                            mutableVector.add(node);
                                                        }
                                                        node = null;
                                                    }
                                                    if (mutableVector != null) {
                                                        mutableVector.add(next$iv$iv$iv);
                                                    }
                                                    mutableVector2 = mutableVector;
                                                    count$iv$iv$iv2 = count$iv$iv$iv;
                                                }
                                            }
                                            node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                            nodePop = node;
                                            dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv2;
                                        }
                                        Modifier.Node node2 = nodePop;
                                        if (count$iv$iv$iv2 == 1) {
                                            type$iv2 = type$iv3;
                                            $i$f$withoutReadObservation3 = $i$f$withoutReadObservation2;
                                            nodePop = node2;
                                        } else {
                                            nodePop = DelegatableNodeKt.pop(mutableVector2);
                                            type$iv2 = type$iv3;
                                            $i$f$withoutReadObservation3 = $i$f$withoutReadObservation2;
                                        }
                                    }
                                }
                                nodePop = DelegatableNodeKt.pop(mutableVector2);
                                type$iv2 = type$iv3;
                                $i$f$withoutReadObservation3 = $i$f$withoutReadObservation2;
                            }
                            type$iv = type$iv2;
                            $i$f$withoutReadObservation = $i$f$withoutReadObservation3;
                        } else {
                            i = i2;
                            type$iv = type$iv2;
                            $i$f$withoutReadObservation = $i$f$withoutReadObservation3;
                        }
                        if (node$iv$iv == stopNode$iv$iv) {
                            break;
                        }
                        node$iv$iv = node$iv$iv.getChild();
                        i2 = i;
                        type$iv2 = type$iv;
                        $i$f$withoutReadObservation3 = $i$f$withoutReadObservation;
                    } else {
                        break;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                this_$iv.restoreNonObservable(previousSnapshot$iv, newSnapshot$iv, readObserver);
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public final void onUnplaced() {
        int type$iv;
        NodeCoordinator this_$iv;
        boolean dispatchAgain$iv$iv$iv;
        MutableVector mutableVector;
        Modifier.Node node;
        int count$iv$iv$iv;
        MutableVector mutableVector2;
        if (!m7069hasNodeH91voCI(NodeKind.m7100constructorimpl(1048576))) {
            return;
        }
        int type$iv2 = NodeKind.m7100constructorimpl(1048576);
        NodeCoordinator this_$iv2 = this;
        boolean includeTail$iv$iv = NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type$iv2);
        Modifier.Node stopNode$iv$iv = this_$iv2.getTail();
        if (includeTail$iv$iv || (stopNode$iv$iv = stopNode$iv$iv.getParent()) != null) {
            Modifier.Node node$iv$iv = this_$iv2.headNode(includeTail$iv$iv);
            while (node$iv$iv != null && (node$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                    Modifier.Node it$iv = node$iv$iv;
                    int kind$iv$iv = type$iv2;
                    MutableVector mutableVector3 = null;
                    type$iv = type$iv2;
                    Modifier.Node nodePop = it$iv;
                    while (nodePop != null) {
                        NodeCoordinator this_$iv3 = this_$iv2;
                        if (nodePop instanceof UnplacedAwareModifierNode) {
                            UnplacedAwareModifierNode it = (UnplacedAwareModifierNode) nodePop;
                            it.onUnplaced();
                            dispatchAgain$iv$iv$iv = false;
                        } else {
                            dispatchAgain$iv$iv$iv = true;
                        }
                        if (dispatchAgain$iv$iv$iv) {
                            Modifier.Node this_$iv$iv$iv$iv = nodePop;
                            int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                            if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                int count$iv$iv$iv2 = 0;
                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv$iv != null) {
                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                    int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv2 == 0) {
                                        mutableVector = mutableVector3;
                                        node = nodePop;
                                    } else {
                                        count$iv$iv$iv2++;
                                        Modifier.Node node2 = nodePop;
                                        if (count$iv$iv$iv2 == 1) {
                                            mutableVector = mutableVector3;
                                            node = next$iv$iv$iv;
                                        } else {
                                            if (mutableVector3 != null) {
                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                mutableVector2 = mutableVector3;
                                            } else {
                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                mutableVector2 = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (node2 == null) {
                                                node = node2;
                                            } else {
                                                if (mutableVector2 != null) {
                                                    mutableVector2.add(node2);
                                                }
                                                node = null;
                                            }
                                            if (mutableVector2 != null) {
                                                mutableVector2.add(next$iv$iv$iv);
                                            }
                                            mutableVector = mutableVector2;
                                            count$iv$iv$iv2 = count$iv$iv$iv;
                                        }
                                    }
                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                    nodePop = node;
                                    mutableVector3 = mutableVector;
                                }
                                Modifier.Node node3 = nodePop;
                                if (count$iv$iv$iv2 == 1) {
                                    this_$iv2 = this_$iv3;
                                    nodePop = node3;
                                }
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector3);
                        this_$iv2 = this_$iv3;
                    }
                    this_$iv = this_$iv2;
                } else {
                    type$iv = type$iv2;
                    this_$iv = this_$iv2;
                }
                if (node$iv$iv == stopNode$iv$iv) {
                    return;
                }
                node$iv$iv = node$iv$iv.getChild();
                type$iv2 = type$iv;
                this_$iv2 = this_$iv;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo6784placeAtf8xVGno(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock) {
        if (this.forcePlaceWithLookaheadOffset) {
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            m7075placeSelfMLgxB_4(lookaheadDelegate.getPosition(), zIndex, layerBlock, null);
            return;
        }
        m7075placeSelfMLgxB_4(position, zIndex, layerBlock, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.compose.ui.layout.Placeable
    /* JADX INFO: renamed from: placeAt-f8xVGno */
    public void mo6846placeAtf8xVGno(long position, float zIndex, GraphicsLayer layer) {
        if (this.forcePlaceWithLookaheadOffset) {
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            m7075placeSelfMLgxB_4(lookaheadDelegate.getPosition(), zIndex, null, layer);
            return;
        }
        m7075placeSelfMLgxB_4(position, zIndex, null, layer);
    }

    /* JADX INFO: renamed from: placeSelf-MLgxB_4, reason: not valid java name */
    private final void m7075placeSelfMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer explicitLayer) {
        if (explicitLayer != null) {
            boolean value$iv = layerBlock == null;
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalArgumentException("both ways to create layers shouldn't be used together");
            }
            if (this.explicitLayer != explicitLayer) {
                this.explicitLayer = null;
                updateLayerBlock$default(this, null, false, 2, null);
                this.explicitLayer = explicitLayer;
            }
            if (this.layer == null) {
                OwnedLayer $this$placeSelf_MLgxB_4_u24lambda_u241 = LayoutNodeKt.requireOwner(getLayoutNode()).createLayer(getDrawBlock(), this.invalidateParentLayer, explicitLayer);
                $this$placeSelf_MLgxB_4_u24lambda_u241.mo7162resizeozmzZPI(getMeasuredSize());
                $this$placeSelf_MLgxB_4_u24lambda_u241.mo7161movegyyYBs(position);
                this.layer = $this$placeSelf_MLgxB_4_u24lambda_u241;
                getLayoutNode().setInnerLayerCoordinatorIsDirty$ui(true);
                this.invalidateParentLayer.invoke();
            }
        } else {
            if (this.explicitLayer != null) {
                this.explicitLayer = null;
                updateLayerBlock$default(this, null, false, 2, null);
            }
            updateLayerBlock$default(this, layerBlock, false, 2, null);
        }
        if (!IntOffset.m8277equalsimpl0(getPosition(), position)) {
            LayoutNodeKt.requireOwner(getLayoutNode()).voteFrameRate(FrameRateCategory.INSTANCE.m4745getHighNSsRyOo());
            m7091setPositiongyyYBs(position);
            OwnedLayer layer = this.layer;
            if (layer != null) {
                layer.mo7161movegyyYBs(position);
            } else {
                NodeCoordinator nodeCoordinator = this.wrappedBy;
                if (nodeCoordinator != null) {
                    nodeCoordinator.invalidateLayer();
                }
            }
            getLayoutNode().onCoordinatorRectChanged$ui(this);
            invalidateAlignmentLinesFromPositionChange(this);
            Owner owner = getLayoutNode().getOwner();
            if (owner != null) {
                owner.onLayoutChange(getLayoutNode());
            }
        }
        this.zIndex = zIndex;
        if (this == getLayoutNode().getOuterCoordinator$ui()) {
            LayoutNodeKt.requireOwner(getLayoutNode()).getRectManager().recalculateRectIfDirty(getLayoutNode());
        }
        if (!getIsPlacingForAlignment()) {
            captureRulersIfNeeded$ui(getMeasureResult$ui());
        }
    }

    public final void releaseLayer() {
        if (this.layer != null) {
            if (this.explicitLayer != null) {
                this.explicitLayer = null;
            }
            updateLayerBlock$default(this, null, false, 2, null);
            LayoutNode.requestRelayout$ui$default(getLayoutNode(), false, 1, null);
        }
    }

    /* JADX INFO: renamed from: placeSelfApparentToRealOffset-MLgxB_4, reason: not valid java name */
    public final void m7090placeSelfApparentToRealOffsetMLgxB_4(long position, float zIndex, Function1<? super GraphicsLayerScope, Unit> layerBlock, GraphicsLayer layer) {
        m7075placeSelfMLgxB_4(IntOffset.m8282plusqkQi6aY(position, getApparentToRealOffset()), zIndex, layerBlock, layer);
    }

    public final void draw(Canvas canvas, GraphicsLayer graphicsLayer) {
        OwnedLayer layer = this.layer;
        if (layer != null) {
            layer.drawLayer(canvas, graphicsLayer);
            return;
        }
        float x = IntOffset.m8278getXimpl(getPosition());
        float y = IntOffset.m8279getYimpl(getPosition());
        canvas.translate(x, y);
        drawContainedDrawModifiers(canvas, graphicsLayer);
        canvas.translate(-x, -y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void drawContainedDrawModifiers(Canvas canvas, GraphicsLayer graphicsLayer) {
        Modifier.Node head = m7086headH91voCI(NodeKind.m7100constructorimpl(4));
        if (head == null) {
            performDraw(canvas, graphicsLayer);
        } else {
            LayoutNodeDrawScope drawScope = getLayoutNode().getMDrawScope$ui();
            drawScope.m7021draweZhPAX0$ui(canvas, IntSizeKt.m8333toSizeozmzZPI(mo6791getSizeYbymL2g()), this, head, graphicsLayer);
        }
    }

    public void performDraw(Canvas canvas, GraphicsLayer graphicsLayer) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.draw(canvas, graphicsLayer);
        }
    }

    public final void onPlaced() {
        int type$iv;
        NodeCoordinator this_$iv;
        int i;
        int i2;
        boolean dispatchAgain$iv$iv$iv;
        boolean dispatchAgain$iv$iv$iv2;
        MutableVector mutableVector;
        Modifier.Node node;
        int count$iv$iv$iv;
        int type$iv2 = NodeKind.m7100constructorimpl(4194304);
        NodeCoordinator this_$iv2 = this;
        int count$iv$iv$iv2 = 0;
        boolean includeTail$iv$iv = NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(type$iv2);
        Modifier.Node stopNode$iv$iv = this_$iv2.getTail();
        if (includeTail$iv$iv || (stopNode$iv$iv = stopNode$iv$iv.getParent()) != null) {
            Modifier.Node node$iv$iv = this_$iv2.headNode(includeTail$iv$iv);
            while (node$iv$iv != null && (node$iv$iv.getAggregateChildKindSet() & type$iv2) != 0) {
                if ((node$iv$iv.getKindSet() & type$iv2) != 0) {
                    Modifier.Node it$iv = node$iv$iv;
                    int kind$iv$iv = type$iv2;
                    MutableVector mutableVector2 = null;
                    type$iv = type$iv2;
                    Modifier.Node nodePop = it$iv;
                    while (nodePop != null) {
                        NodeCoordinator this_$iv3 = this_$iv2;
                        if (nodePop instanceof LayoutAwareModifierNode) {
                            LayoutAwareModifierNode it = (LayoutAwareModifierNode) nodePop;
                            i2 = count$iv$iv$iv2;
                            it.onPlaced(this);
                            dispatchAgain$iv$iv$iv = false;
                        } else {
                            i2 = count$iv$iv$iv2;
                            dispatchAgain$iv$iv$iv = true;
                        }
                        if (dispatchAgain$iv$iv$iv) {
                            Modifier.Node this_$iv$iv$iv$iv = nodePop;
                            int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                            if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                int count$iv$iv$iv3 = 0;
                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv$iv != null) {
                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                    int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv2 == 0) {
                                        dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                        mutableVector = mutableVector2;
                                        node = nodePop;
                                    } else {
                                        count$iv$iv$iv3++;
                                        Modifier.Node node2 = nodePop;
                                        if (count$iv$iv$iv3 == 1) {
                                            dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                            mutableVector = mutableVector2;
                                            node = next$iv$iv$iv;
                                        } else {
                                            if (mutableVector2 != null) {
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                mutableVector = mutableVector2;
                                            } else {
                                                dispatchAgain$iv$iv$iv2 = dispatchAgain$iv$iv$iv;
                                                count$iv$iv$iv = count$iv$iv$iv3;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            }
                                            if (node2 == null) {
                                                node = node2;
                                            } else {
                                                if (mutableVector != null) {
                                                    mutableVector.add(node2);
                                                }
                                                node = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(next$iv$iv$iv);
                                            }
                                            count$iv$iv$iv3 = count$iv$iv$iv;
                                        }
                                    }
                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                    nodePop = node;
                                    mutableVector2 = mutableVector;
                                    dispatchAgain$iv$iv$iv = dispatchAgain$iv$iv$iv2;
                                }
                                Modifier.Node node3 = nodePop;
                                if (count$iv$iv$iv3 != 1) {
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    this_$iv2 = this_$iv3;
                                    count$iv$iv$iv2 = i2;
                                } else {
                                    this_$iv2 = this_$iv3;
                                    count$iv$iv$iv2 = i2;
                                    nodePop = node3;
                                }
                            }
                        }
                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                        this_$iv2 = this_$iv3;
                        count$iv$iv$iv2 = i2;
                    }
                    this_$iv = this_$iv2;
                    i = count$iv$iv$iv2;
                } else {
                    type$iv = type$iv2;
                    this_$iv = this_$iv2;
                    i = count$iv$iv$iv2;
                }
                if (node$iv$iv == stopNode$iv$iv) {
                    return;
                }
                node$iv$iv = node$iv$iv.getChild();
                type$iv2 = type$iv;
                this_$iv2 = this_$iv;
                count$iv$iv$iv2 = i;
            }
        }
    }

    private final Function2<Canvas, GraphicsLayer, Unit> getDrawBlock() {
        Function2 block = this._drawBlock;
        if (block == null) {
            final Function0<Unit> function0 = new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$drawBlock$drawBlockCallToDrawModifiers$1
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    NodeCoordinator nodeCoordinator = this.this$0;
                    Canvas canvas = this.this$0.drawBlockCanvas;
                    Intrinsics.checkNotNull(canvas);
                    nodeCoordinator.drawContainedDrawModifiers(canvas, this.this$0.drawBlockParentLayer);
                }
            };
            Function2<Canvas, GraphicsLayer, Unit> function2 = new Function2<Canvas, GraphicsLayer, Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$drawBlock$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Canvas canvas, GraphicsLayer graphicsLayer) {
                    invoke2(canvas, graphicsLayer);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Canvas canvas, GraphicsLayer parentLayer) {
                    boolean zIsPlaced = this.this$0.getLayoutNode().isPlaced();
                    NodeCoordinator nodeCoordinator = this.this$0;
                    if (zIsPlaced) {
                        nodeCoordinator.drawBlockCanvas = canvas;
                        this.this$0.drawBlockParentLayer = parentLayer;
                        OwnerSnapshotObserver this_$iv = this.this$0.getSnapshotObserver();
                        NodeCoordinator nodeCoordinator2 = this.this$0;
                        Function1 onChanged$iv = NodeCoordinator.onCommitAffectingLayer;
                        this_$iv.observer.observeReads(nodeCoordinator2, onChanged$iv, function0);
                        this.this$0.lastLayerDrawingWasSkipped = false;
                        return;
                    }
                    nodeCoordinator.lastLayerDrawingWasSkipped = true;
                }
            };
            this._drawBlock = function2;
            return function2;
        }
        return block;
    }

    public static /* synthetic */ void updateLayerBlock$default(NodeCoordinator nodeCoordinator, Function1 function1, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateLayerBlock");
        }
        if ((i & 2) != 0) {
            z = false;
        }
        nodeCoordinator.updateLayerBlock(function1, z);
    }

    public final void updateLayerBlock(Function1<? super GraphicsLayerScope, Unit> layerBlock, boolean forceUpdateLayerParameters) {
        Owner owner;
        boolean value$iv = layerBlock == null || this.explicitLayer == null;
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalArgumentException("layerBlock can't be provided when explicitLayer is provided");
        }
        LayoutNode layoutNode = getLayoutNode();
        boolean updateParameters = (!forceUpdateLayerParameters && this.layerBlock == layerBlock && Intrinsics.areEqual(this.layerDensity, layoutNode.getDensity()) && this.layerLayoutDirection == layoutNode.getLayoutDirection()) ? false : true;
        this.layerDensity = layoutNode.getDensity();
        this.layerLayoutDirection = layoutNode.getLayoutDirection();
        if (layoutNode.isAttached() && layerBlock != null) {
            this.layerBlock = layerBlock;
            if (this.layer == null) {
                OwnedLayer $this$updateLayerBlock_u24lambda_u241 = Owner.createLayer$default(LayoutNodeKt.requireOwner(layoutNode), getDrawBlock(), this.invalidateParentLayer, null, 4, null);
                $this$updateLayerBlock_u24lambda_u241.mo7162resizeozmzZPI(getMeasuredSize());
                $this$updateLayerBlock_u24lambda_u241.mo7161movegyyYBs(getPosition());
                this.layer = $this$updateLayerBlock_u24lambda_u241;
                updateLayerParameters$default(this, false, 1, null);
                layoutNode.setInnerLayerCoordinatorIsDirty$ui(true);
                this.invalidateParentLayer.invoke();
                return;
            }
            if (updateParameters) {
                updateLayerParameters$default(this, false, 1, null);
                return;
            }
            return;
        }
        this.layerBlock = null;
        OwnedLayer it = this.layer;
        if (it != null) {
            if (!MatrixKt.m5582isIdentity58bKbWc(it.mo7157getUnderlyingMatrixsQKQjiQ())) {
                layoutNode.onCoordinatorRectChanged$ui(this);
            }
            it.destroy();
            this.layer = null;
            layoutNode.setInnerLayerCoordinatorIsDirty$ui(true);
            this.invalidateParentLayer.invoke();
            if (isAttached() && layoutNode.isPlaced() && (owner = layoutNode.getOwner()) != null) {
                owner.onLayoutChange(layoutNode);
            }
        }
        this.lastLayerDrawingWasSkipped = false;
    }

    static /* synthetic */ void updateLayerParameters$default(NodeCoordinator nodeCoordinator, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateLayerParameters");
        }
        if ((i & 1) != 0) {
            z = true;
        }
        nodeCoordinator.updateLayerParameters(z);
    }

    private final void updateLayerParameters(boolean invokeOnLayoutChange) {
        Owner owner;
        if (this.explicitLayer != null) {
            return;
        }
        OwnedLayer layer = this.layer;
        if (layer != null) {
            final Function1<? super GraphicsLayerScope, Unit> function1 = this.layerBlock;
            if (function1 != null) {
                graphicsLayerScope.reset();
                graphicsLayerScope.setGraphicsDensity$ui(getLayoutNode().getDensity());
                graphicsLayerScope.setLayoutDirection$ui(getLayoutNode().getLayoutDirection());
                graphicsLayerScope.m5641setSizeuvyYCjk(IntSizeKt.m8333toSizeozmzZPI(mo6791getSizeYbymL2g()));
                OwnerSnapshotObserver this_$iv = getSnapshotObserver();
                this_$iv.observer.observeReads(this, onCommitAffectingLayerParams, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator.updateLayerParameters.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function1.invoke(NodeCoordinator.graphicsLayerScope);
                        boolean hasShapeChanged = !Intrinsics.areEqual(this.getLastShape(), NodeCoordinator.graphicsLayerScope.getShape());
                        boolean hasClipChanged = this.getLastClip() != NodeCoordinator.graphicsLayerScope.getClip();
                        if (hasShapeChanged || hasClipChanged) {
                            this.setLastShape$ui(NodeCoordinator.graphicsLayerScope.getShape());
                            this.setLastClip$ui(NodeCoordinator.graphicsLayerScope.getClip());
                            if (this.getWasLayerBlockInvoked() && (hasClipChanged || (this.getLastClip() && hasShapeChanged))) {
                                this.getLayoutNode().invalidateSemantics$ui();
                            }
                        }
                        this.setWasLayerBlockInvoked$ui(true);
                        NodeCoordinator.graphicsLayerScope.updateOutline$ui();
                    }
                });
                LayerPositionalProperties it = this.layerPositionalProperties;
                if (it == null) {
                    it = new LayerPositionalProperties();
                    this.layerPositionalProperties = it;
                }
                tmpLayerPositionalProperties.copyFrom(it);
                it.copyFrom(graphicsLayerScope);
                layer.updateLayerProperties(graphicsLayerScope);
                boolean wasClipping = this.isClipping;
                this.isClipping = graphicsLayerScope.getClip();
                this.lastLayerAlpha = graphicsLayerScope.getAlpha();
                boolean positionalPropertiesChanged = true ^ tmpLayerPositionalProperties.hasSameValuesAs(it);
                if (invokeOnLayoutChange && ((positionalPropertiesChanged || wasClipping != this.isClipping) && (owner = getLayoutNode().getOwner()) != null)) {
                    owner.onLayoutChange(getLayoutNode());
                }
                if (positionalPropertiesChanged) {
                    LayoutNode layoutNode = getLayoutNode();
                    layoutNode.onCoordinatorRectChanged$ui(this);
                    if (layoutNode.getGloballyPositionedObservers() > 0) {
                        LayoutNodeKt.requireOwner(layoutNode).requestOnPositionedCallback(layoutNode);
                        return;
                    }
                    return;
                }
                return;
            }
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("updateLayerParameters requires a non-null layerBlock");
            throw new KotlinNothingValueException();
        }
        Object value$iv = this.layerBlock;
        boolean value$iv2 = value$iv == null;
        if (value$iv2) {
            return;
        }
        InlineClassHelperKt.throwIllegalStateException("null layer with a non-null layerBlock");
    }

    /* JADX INFO: renamed from: getLastLayerDrawingWasSkipped$ui, reason: from getter */
    public final boolean getLastLayerDrawingWasSkipped() {
        return this.lastLayerDrawingWasSkipped;
    }

    public final OwnedLayer getLayer() {
        return this.layer;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public boolean isValidOwnerScope() {
        return (this.layer == null || this.released || !getLayoutNode().isAttached()) ? false : true;
    }

    /* JADX INFO: renamed from: getMinimumTouchTargetSize-NH-jbRc, reason: not valid java name */
    public final long m7085getMinimumTouchTargetSizeNHjbRc() {
        Density $this$getMinimumTouchTargetSize_NH_jbRc_u24lambda_u240 = this.layerDensity;
        return $this$getMinimumTouchTargetSize_NH_jbRc_u24lambda_u240.mo433toSizeXkaWNTQ(getLayoutNode().getViewConfiguration().mo7015getMinimumTouchTargetSizeMYxV2XQ());
    }

    /* JADX INFO: renamed from: hitTest-qzLsGqo, reason: not valid java name */
    public final void m7087hitTestqzLsGqo(HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
        boolean z;
        Modifier.Node head = m7086headH91voCI(hitTestSource.mo7096entityTypeOLwlOKw());
        boolean isHitInMinimumTouchTargetBetter = false;
        if (!m7094withinLayerBoundsk4lQ0M(pointerPosition)) {
            if (PointerType.m6723equalsimpl0(pointerType, PointerType.INSTANCE.m6730getTouchT8wyACA())) {
                float distanceFromEdge = m7082distanceInMinimumTouchTargettz77jQw(pointerPosition, m7085getMinimumTouchTargetSizeNHjbRc());
                if (((Integer.MAX_VALUE & Float.floatToRawIntBits(distanceFromEdge)) < 2139095040) && hitTestResult.isHitInMinimumTouchTargetBetter(distanceFromEdge, false)) {
                    m7071hitNearFh5PU_I(head, hitTestSource, pointerPosition, hitTestResult, pointerType, false, distanceFromEdge);
                    return;
                }
                return;
            }
            return;
        }
        if (head == null) {
            mo7001hitTestChildqzLsGqo(hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
            return;
        }
        if (m7088isPointerInBoundsk4lQ0M(pointerPosition)) {
            m7070hit5ShdDok(head, hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
            return;
        }
        float distanceFromEdge2 = !PointerType.m6723equalsimpl0(pointerType, PointerType.INSTANCE.m6730getTouchT8wyACA()) ? Float.POSITIVE_INFINITY : m7082distanceInMinimumTouchTargettz77jQw(pointerPosition, m7085getMinimumTouchTargetSizeNHjbRc());
        float $this$fastIsFinite$iv = distanceFromEdge2;
        if (!((Integer.MAX_VALUE & Float.floatToRawIntBits($this$fastIsFinite$iv)) < 2139095040)) {
            z = isInLayer;
        } else {
            z = isInLayer;
            if (hitTestResult.isHitInMinimumTouchTargetBetter(distanceFromEdge2, z)) {
                isHitInMinimumTouchTargetBetter = true;
            }
        }
        m7074outOfBoundsHit8NAm7pk(head, hitTestSource, pointerPosition, hitTestResult, pointerType, z, distanceFromEdge2, isHitInMinimumTouchTargetBetter);
    }

    /* JADX INFO: renamed from: hit-5ShdDok, reason: not valid java name */
    private final void m7070hit5ShdDok(Modifier.Node $this$hit_u2d5ShdDok, HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
        if ($this$hit_u2d5ShdDok != null) {
            if (hitTestSource.shouldHitTest($this$hit_u2d5ShdDok)) {
                int startDepth$iv$iv$iv = hitTestResult.hitDepth;
                hitTestResult.removeNodesInRange(hitTestResult.hitDepth + 1, hitTestResult.size());
                hitTestResult.hitDepth++;
                hitTestResult.values.add($this$hit_u2d5ShdDok);
                hitTestResult.distanceFromEdgeAndFlags.add(HitTestResultKt.DistanceAndFlags(-1.0f, isInLayer, false));
                m7070hit5ShdDok(NodeCoordinatorKt.m7098nextUntilhw7D004($this$hit_u2d5ShdDok, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
                hitTestResult.hitDepth = startDepth$iv$iv$iv;
                return;
            }
            m7070hit5ShdDok(NodeCoordinatorKt.m7098nextUntilhw7D004($this$hit_u2d5ShdDok, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
            return;
        }
        mo7001hitTestChildqzLsGqo(hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: outOfBoundsHit-8NAm7pk, reason: not valid java name */
    public final void m7074outOfBoundsHit8NAm7pk(final Modifier.Node $this$outOfBoundsHit_u2d8NAm7pk, final HitTestSource hitTestSource, final long pointerPosition, final HitTestResult hitTestResult, final int pointerType, final boolean isInLayer, final float distanceFromEdge, final boolean isHitInMinimumTouchTargetBetter) {
        if ($this$outOfBoundsHit_u2d8NAm7pk == null) {
            mo7001hitTestChildqzLsGqo(hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
            return;
        }
        if (!hitTestSource.shouldHitTest($this$outOfBoundsHit_u2d8NAm7pk)) {
            m7074outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m7098nextUntilhw7D004($this$outOfBoundsHit_u2d8NAm7pk, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge, isHitInMinimumTouchTargetBetter);
            return;
        }
        if (m7072isInExpandedTouchBoundsThDn1k($this$outOfBoundsHit_u2d8NAm7pk, pointerPosition, pointerType)) {
            hitTestResult.hitExpandedTouchBounds($this$outOfBoundsHit_u2d8NAm7pk, isInLayer, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$outOfBoundsHit$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.m7074outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m7098nextUntilhw7D004($this$outOfBoundsHit_u2d8NAm7pk, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge, isHitInMinimumTouchTargetBetter);
                }
            });
        } else if (isHitInMinimumTouchTargetBetter) {
            m7071hitNearFh5PU_I($this$outOfBoundsHit_u2d8NAm7pk, hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge);
        } else {
            m7076speculativeHitFh5PU_I($this$outOfBoundsHit_u2d8NAm7pk, hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge);
        }
    }

    /* JADX INFO: renamed from: hitNear-Fh5PU_I, reason: not valid java name */
    private final void m7071hitNearFh5PU_I(Modifier.Node $this$hitNear_u2dFh5PU_I, HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer, float distanceFromEdge) {
        if ($this$hitNear_u2dFh5PU_I != null) {
            if (hitTestSource.shouldHitTest($this$hitNear_u2dFh5PU_I)) {
                int startDepth$iv$iv = hitTestResult.hitDepth;
                hitTestResult.removeNodesInRange(hitTestResult.hitDepth + 1, hitTestResult.size());
                hitTestResult.hitDepth++;
                hitTestResult.values.add($this$hitNear_u2dFh5PU_I);
                hitTestResult.distanceFromEdgeAndFlags.add(HitTestResultKt.DistanceAndFlags(distanceFromEdge, isInLayer, false));
                m7074outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m7098nextUntilhw7D004($this$hitNear_u2dFh5PU_I, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge, true);
                hitTestResult.hitDepth = startDepth$iv$iv;
                return;
            }
            m7071hitNearFh5PU_I(NodeCoordinatorKt.m7098nextUntilhw7D004($this$hitNear_u2dFh5PU_I, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge);
            return;
        }
        mo7001hitTestChildqzLsGqo(hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
    }

    /* JADX INFO: renamed from: speculativeHit-Fh5PU_I, reason: not valid java name */
    private final void m7076speculativeHitFh5PU_I(final Modifier.Node $this$speculativeHit_u2dFh5PU_I, final HitTestSource hitTestSource, final long pointerPosition, final HitTestResult hitTestResult, final int pointerType, final boolean isInLayer, final float distanceFromEdge) {
        if ($this$speculativeHit_u2dFh5PU_I == null) {
            mo7001hitTestChildqzLsGqo(hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer);
            return;
        }
        if (!hitTestSource.shouldHitTest($this$speculativeHit_u2dFh5PU_I)) {
            m7076speculativeHitFh5PU_I(NodeCoordinatorKt.m7098nextUntilhw7D004($this$speculativeHit_u2dFh5PU_I, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge);
        } else if (hitTestSource.interceptOutOfBoundsChildEvents($this$speculativeHit_u2dFh5PU_I)) {
            hitTestResult.speculativeHit($this$speculativeHit_u2dFh5PU_I, distanceFromEdge, isInLayer, new Function0<Unit>() { // from class: androidx.compose.ui.node.NodeCoordinator$speculativeHit$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    this.this$0.m7074outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m7098nextUntilhw7D004($this$speculativeHit_u2dFh5PU_I, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge, false);
                }
            });
        } else {
            m7074outOfBoundsHit8NAm7pk(NodeCoordinatorKt.m7098nextUntilhw7D004($this$speculativeHit_u2dFh5PU_I, hitTestSource.mo7096entityTypeOLwlOKw(), NodeKind.m7100constructorimpl(2)), hitTestSource, pointerPosition, hitTestResult, pointerType, isInLayer, distanceFromEdge, false);
        }
    }

    /* JADX INFO: renamed from: isInExpandedTouchBounds-ThD-n1k, reason: not valid java name */
    private final boolean m7072isInExpandedTouchBoundsThDn1k(Modifier.Node $this$isInExpandedTouchBounds_u2dThD_u2dn1k, long pointerPosition, int pointerType) {
        boolean dispatchAgain$iv$iv;
        DelegatingNode this_$iv$iv$iv;
        int count$iv$iv;
        MutableVector mutableVector;
        if ($this$isInExpandedTouchBounds_u2dThD_u2dn1k == null) {
            return false;
        }
        if (!PointerType.m6723equalsimpl0(pointerType, PointerType.INSTANCE.m6729getStylusT8wyACA()) && !PointerType.m6723equalsimpl0(pointerType, PointerType.INSTANCE.m6727getEraserT8wyACA())) {
            return false;
        }
        int kind$iv = NodeKind.m7100constructorimpl(16);
        MutableVector mutableVector2 = null;
        Modifier.Node this_$iv$iv$iv2 = $this$isInExpandedTouchBounds_u2dThD_u2dn1k;
        while (this_$iv$iv$iv2 != null) {
            if (this_$iv$iv$iv2 instanceof PointerInputModifierNode) {
                PointerInputModifierNode it = (PointerInputModifierNode) this_$iv$iv$iv2;
                long expansion = it.mo1708getTouchBoundsExpansionRZrCHBk();
                int bits$iv$iv$iv = (int) (pointerPosition >> 32);
                if (Float.intBitsToFloat(bits$iv$iv$iv) >= (-TouchBoundsExpansion.m7186computeLeftimpl$ui(expansion, getLayoutDirection()))) {
                    int bits$iv$iv$iv2 = (int) (pointerPosition >> 32);
                    if (Float.intBitsToFloat(bits$iv$iv$iv2) < getMeasuredWidth() + TouchBoundsExpansion.m7187computeRightimpl$ui(expansion, getLayoutDirection())) {
                        int bits$iv$iv$iv3 = (int) (pointerPosition & 4294967295L);
                        if (Float.intBitsToFloat(bits$iv$iv$iv3) >= (-TouchBoundsExpansion.m7194getTopimpl(expansion))) {
                            long arg0$iv = pointerPosition & 4294967295L;
                            int bits$iv$iv$iv4 = (int) arg0$iv;
                            if (Float.intBitsToFloat(bits$iv$iv$iv4) < getMeasuredHeight() + TouchBoundsExpansion.m7191getBottomimpl(expansion)) {
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
            int kind$iv2 = kind$iv;
            int i = 1;
            boolean dispatchAgain$iv$iv2 = true;
            int kind$iv$iv$iv = (this_$iv$iv$iv2.getKindSet() & kind$iv) != 0 ? 1 : 0;
            if (kind$iv$iv$iv == 0 || !(this_$iv$iv$iv2 instanceof DelegatingNode)) {
                this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                kind$iv = kind$iv2;
            } else {
                int count$iv$iv2 = 0;
                DelegatingNode this_$iv$iv$iv3 = (DelegatingNode) this_$iv$iv$iv2;
                Modifier.Node node$iv$iv$iv = this_$iv$iv$iv3.getDelegate();
                while (node$iv$iv$iv != null) {
                    Modifier.Node next$iv$iv = node$iv$iv$iv;
                    int kind$iv$iv$iv2 = (next$iv$iv.getKindSet() & kind$iv) != 0 ? i : 0;
                    if (kind$iv$iv$iv2 != 0) {
                        count$iv$iv2++;
                        if (count$iv$iv2 == i) {
                            this_$iv$iv$iv2 = next$iv$iv;
                            dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                            this_$iv$iv$iv = this_$iv$iv$iv3;
                        } else {
                            if (mutableVector2 == null) {
                                dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                count$iv$iv = count$iv$iv2;
                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                            } else {
                                dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                                count$iv$iv = count$iv$iv2;
                                this_$iv$iv$iv = this_$iv$iv$iv3;
                                mutableVector = mutableVector2;
                            }
                            mutableVector2 = mutableVector;
                            Modifier.Node theNode$iv$iv = this_$iv$iv$iv2;
                            if (theNode$iv$iv != null) {
                                if (mutableVector2 != null) {
                                    mutableVector2.add(theNode$iv$iv);
                                }
                                this_$iv$iv$iv2 = null;
                            }
                            if (mutableVector2 != null) {
                                mutableVector2.add(next$iv$iv);
                            }
                            count$iv$iv2 = count$iv$iv;
                        }
                    } else {
                        dispatchAgain$iv$iv = dispatchAgain$iv$iv2;
                        this_$iv$iv$iv = this_$iv$iv$iv3;
                    }
                    node$iv$iv$iv = node$iv$iv$iv.getChild();
                    dispatchAgain$iv$iv2 = dispatchAgain$iv$iv;
                    this_$iv$iv$iv3 = this_$iv$iv$iv;
                    i = 1;
                }
                if (count$iv$iv2 == 1) {
                    kind$iv = kind$iv2;
                } else {
                    this_$iv$iv$iv2 = DelegatableNodeKt.pop(mutableVector2);
                    kind$iv = kind$iv2;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: hitTestChild-qzLsGqo */
    public void mo7001hitTestChildqzLsGqo(HitTestSource hitTestSource, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer) {
        NodeCoordinator wrapped = this.wrapped;
        if (wrapped != null) {
            long positionInWrapped = m7068fromParentPosition8S9VItk$default(wrapped, pointerPosition, false, 2, null);
            wrapped.m7087hitTestqzLsGqo(hitTestSource, positionInWrapped, hitTestResult, pointerType, isInLayer);
        }
    }

    public final Rect touchBoundsInRoot() {
        if (!isAttached()) {
            return Rect.INSTANCE.getZero();
        }
        LayoutCoordinates root = LayoutCoordinatesKt.findRootCoordinates(this);
        MutableRect bounds = getRectCache();
        long padding = m7081calculateMinimumTouchTargetPaddingE7KxVPU(m7085getMinimumTouchTargetSizeNHjbRc());
        int bits$iv$iv$iv = (int) (padding >> 32);
        bounds.setLeft(-Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (padding & 4294967295L);
        bounds.setTop(-Float.intBitsToFloat(bits$iv$iv$iv2));
        int bits$iv$iv$iv3 = (int) (padding >> 32);
        bounds.setRight(getMeasuredWidth() + Float.intBitsToFloat(bits$iv$iv$iv3));
        int bits$iv$iv$iv4 = (int) (padding & 4294967295L);
        bounds.setBottom(getMeasuredHeight() + Float.intBitsToFloat(bits$iv$iv$iv4));
        NodeCoordinator coordinator = this;
        while (coordinator != root) {
            coordinator.rectInParent$ui(bounds, false, true);
            if (bounds.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
            NodeCoordinator nodeCoordinator = coordinator.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator);
            coordinator = nodeCoordinator;
        }
        return MutableRectKt.toRect(bounds);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: screenToLocal-MK-Hz9U */
    public long mo6797screenToLocalMKHz9U(long relativeToScreen) {
        boolean value$iv = isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        Owner owner = LayoutNodeKt.requireOwner(getLayoutNode());
        long positionInRoot = owner.mo6733screenToLocalMKHz9U(relativeToScreen);
        LayoutCoordinates root = LayoutCoordinatesKt.findRootCoordinates(this);
        return mo6792localPositionOfR5De75A(root, positionInRoot);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToScreen-MK-Hz9U */
    public long mo6795localToScreenMKHz9U(long relativeToLocal) {
        boolean value$iv = isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        long positionInRoot = mo6794localToRootMKHz9U(relativeToLocal);
        Owner owner = LayoutNodeKt.requireOwner(getLayoutNode());
        return owner.mo6732localToScreenMKHz9U(positionInRoot);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: windowToLocal-MK-Hz9U */
    public long mo6800windowToLocalMKHz9U(long relativeToWindow) {
        boolean value$iv = isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        LayoutCoordinates root = LayoutCoordinatesKt.findRootCoordinates(this);
        long positionInRoot = Offset.m5072minusMKHz9U(LayoutNodeKt.requireOwner(getLayoutNode()).mo7164calculateLocalPositionMKHz9U(relativeToWindow), LayoutCoordinatesKt.positionInRoot(root));
        return mo6792localPositionOfR5De75A(root, positionInRoot);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToWindow-MK-Hz9U */
    public long mo6796localToWindowMKHz9U(long relativeToLocal) {
        long positionInRoot = mo6794localToRootMKHz9U(relativeToLocal);
        Owner owner = LayoutNodeKt.requireOwner(getLayoutNode());
        return owner.mo7165calculatePositionInWindowMKHz9U(positionInRoot);
    }

    private final NodeCoordinator toCoordinator(LayoutCoordinates $this$toCoordinator) {
        NodeCoordinator coordinator;
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates = $this$toCoordinator instanceof LookaheadLayoutCoordinates ? (LookaheadLayoutCoordinates) $this$toCoordinator : null;
        if (lookaheadLayoutCoordinates != null && (coordinator = lookaheadLayoutCoordinates.getCoordinator()) != null) {
            return coordinator;
        }
        Intrinsics.checkNotNull($this$toCoordinator, "null cannot be cast to non-null type androidx.compose.ui.node.NodeCoordinator");
        return (NodeCoordinator) $this$toCoordinator;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-R5De75A */
    public long mo6792localPositionOfR5De75A(LayoutCoordinates sourceCoordinates, long relativeToSource) {
        return mo6793localPositionOfS_NoaFU(sourceCoordinates, relativeToSource, true);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localPositionOf-S_NoaFU */
    public long mo6793localPositionOfS_NoaFU(LayoutCoordinates sourceCoordinates, long relativeToSource, boolean includeMotionFrameOfReference) {
        if (sourceCoordinates instanceof LookaheadLayoutCoordinates) {
            ((LookaheadLayoutCoordinates) sourceCoordinates).getCoordinator().onCoordinatesUsed$ui();
            long arg0$iv = Offset.m5060constructorimpl(relativeToSource ^ (-9223372034707292160L));
            return Offset.m5060constructorimpl(((LookaheadLayoutCoordinates) sourceCoordinates).mo6793localPositionOfS_NoaFU(this, arg0$iv, includeMotionFrameOfReference) ^ (-9223372034707292160L));
        }
        NodeCoordinator nodeCoordinator = toCoordinator(sourceCoordinates);
        nodeCoordinator.onCoordinatesUsed$ui();
        NodeCoordinator commonAncestor = findCommonAncestor$ui(nodeCoordinator);
        long position = relativeToSource;
        NodeCoordinator coordinator = nodeCoordinator;
        while (coordinator != commonAncestor) {
            position = coordinator.m7092toParentPosition8S9VItk(position, includeMotionFrameOfReference);
            NodeCoordinator nodeCoordinator2 = coordinator.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator2);
            coordinator = nodeCoordinator2;
        }
        return m7067ancestorToLocalS_NoaFU(commonAncestor, position, includeMotionFrameOfReference);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformFrom-EL8BTi8 */
    public void mo6798transformFromEL8BTi8(LayoutCoordinates sourceCoordinates, float[] matrix) {
        NodeCoordinator coordinator = toCoordinator(sourceCoordinates);
        coordinator.onCoordinatesUsed$ui();
        NodeCoordinator commonAncestor = findCommonAncestor$ui(coordinator);
        Matrix.m5566resetimpl(matrix);
        coordinator.m7079transformToAncestorEL8BTi8(commonAncestor, matrix);
        m7078transformFromAncestorEL8BTi8(commonAncestor, matrix);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: transformToScreen-58bKbWc */
    public void mo6799transformToScreen58bKbWc(float[] matrix) {
        Owner owner = LayoutNodeKt.requireOwner(getLayoutNode());
        NodeCoordinator rootCoordinator = toCoordinator(LayoutCoordinatesKt.findRootCoordinates(this));
        m7079transformToAncestorEL8BTi8(rootCoordinator, matrix);
        if (owner instanceof MatrixPositionCalculator) {
            ((MatrixPositionCalculator) owner).mo6556localToScreen58bKbWc(matrix);
            return;
        }
        long screenPosition = LayoutCoordinatesKt.positionOnScreen(rootCoordinator);
        if (!((9223372034707292159L & screenPosition) != androidx.compose.ui.geometry.InlineClassHelperKt.UnspecifiedPackedFloats)) {
            return;
        }
        int bits$iv$iv$iv = (int) (screenPosition >> 32);
        int bits$iv$iv$iv2 = (int) (4294967295L & screenPosition);
        Matrix.m5578translateimpl(matrix, Float.intBitsToFloat(bits$iv$iv$iv), Float.intBitsToFloat(bits$iv$iv$iv2), 0.0f);
    }

    /* JADX INFO: renamed from: transformToAncestor-EL8BTi8, reason: not valid java name */
    private final void m7079transformToAncestorEL8BTi8(NodeCoordinator ancestor, float[] matrix) {
        NodeCoordinator wrapper = this;
        while (!Intrinsics.areEqual(wrapper, ancestor)) {
            OwnedLayer ownedLayer = wrapper.layer;
            if (ownedLayer != null) {
                ownedLayer.mo7163transform58bKbWc(matrix);
            }
            long position = wrapper.getPosition();
            if (!IntOffset.m8277equalsimpl0(position, IntOffset.INSTANCE.m8289getZeronOccac())) {
                Matrix.m5566resetimpl(tmpMatrix);
                Matrix.m5579translateimpl$default(tmpMatrix, IntOffset.m8278getXimpl(position), IntOffset.m8279getYimpl(position), 0.0f, 4, null);
                Matrix.m5576timesAssign58bKbWc(matrix, tmpMatrix);
            }
            NodeCoordinator nodeCoordinator = wrapper.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator);
            wrapper = nodeCoordinator;
        }
    }

    /* JADX INFO: renamed from: transformFromAncestor-EL8BTi8, reason: not valid java name */
    private final void m7078transformFromAncestorEL8BTi8(NodeCoordinator ancestor, float[] matrix) {
        if (!Intrinsics.areEqual(ancestor, this)) {
            NodeCoordinator nodeCoordinator = this.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator);
            nodeCoordinator.m7078transformFromAncestorEL8BTi8(ancestor, matrix);
            if (!IntOffset.m8277equalsimpl0(getPosition(), IntOffset.INSTANCE.m8289getZeronOccac())) {
                Matrix.m5566resetimpl(tmpMatrix);
                Matrix.m5579translateimpl$default(tmpMatrix, -IntOffset.m8278getXimpl(getPosition()), -IntOffset.m8279getYimpl(getPosition()), 0.0f, 4, null);
                Matrix.m5576timesAssign58bKbWc(matrix, tmpMatrix);
            }
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo7158inverseTransform58bKbWc(matrix);
            }
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public Rect localBoundingBoxOf(LayoutCoordinates sourceCoordinates, boolean clipBounds) {
        boolean value$iv = isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        boolean value$iv2 = sourceCoordinates.isAttached();
        if (!value$iv2) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates " + sourceCoordinates + " is not attached!");
        }
        NodeCoordinator srcCoordinator = toCoordinator(sourceCoordinates);
        srcCoordinator.onCoordinatesUsed$ui();
        NodeCoordinator commonAncestor = findCommonAncestor$ui(srcCoordinator);
        MutableRect bounds = getRectCache();
        bounds.setLeft(0.0f);
        bounds.setTop(0.0f);
        long arg0$iv = sourceCoordinates.mo6791getSizeYbymL2g();
        bounds.setRight((int) (arg0$iv >> 32));
        long arg0$iv2 = sourceCoordinates.mo6791getSizeYbymL2g();
        bounds.setBottom((int) (4294967295L & arg0$iv2));
        NodeCoordinator coordinator = srcCoordinator;
        while (coordinator != commonAncestor) {
            boolean clipBounds2 = clipBounds;
            rectInParent$ui$default(coordinator, bounds, clipBounds2, false, 4, null);
            if (bounds.isEmpty()) {
                return Rect.INSTANCE.getZero();
            }
            NodeCoordinator nodeCoordinator = coordinator.wrappedBy;
            Intrinsics.checkNotNull(nodeCoordinator);
            coordinator = nodeCoordinator;
            clipBounds = clipBounds2;
        }
        ancestorToLocal(commonAncestor, bounds, clipBounds);
        return MutableRectKt.toRect(bounds);
    }

    /* JADX INFO: renamed from: ancestorToLocal-S_NoaFU, reason: not valid java name */
    private final long m7067ancestorToLocalS_NoaFU(NodeCoordinator ancestor, long offset, boolean includeMotionFrameOfReference) {
        if (ancestor == this) {
            return offset;
        }
        NodeCoordinator wrappedBy = this.wrappedBy;
        if (wrappedBy == null || Intrinsics.areEqual(ancestor, wrappedBy)) {
            return m7083fromParentPosition8S9VItk(offset, includeMotionFrameOfReference);
        }
        return m7083fromParentPosition8S9VItk(wrappedBy.m7067ancestorToLocalS_NoaFU(ancestor, offset, includeMotionFrameOfReference), includeMotionFrameOfReference);
    }

    private final void ancestorToLocal(NodeCoordinator ancestor, MutableRect rect, boolean clipBounds) {
        if (ancestor == this) {
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.ancestorToLocal(ancestor, rect, clipBounds);
        }
        fromParentRect(rect, clipBounds);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* JADX INFO: renamed from: localToRoot-MK-Hz9U */
    public long mo6794localToRootMKHz9U(long relativeToLocal) {
        boolean value$iv = isAttached();
        if (!value$iv) {
            InlineClassHelperKt.throwIllegalStateException(ExpectAttachedLayoutCoordinates);
        }
        onCoordinatesUsed$ui();
        long position = relativeToLocal;
        for (NodeCoordinator coordinator = this; coordinator != null; coordinator = coordinator.wrappedBy) {
            LayoutNode layoutNode = coordinator.getLayoutNode();
            if (coordinator == layoutNode.getOuterCoordinator$ui() && !layoutNode.getHasPositionalLayerTransformationsInOffsetFromRoot()) {
                long offsetFromRectList = LayoutNodeKt.requireOwner(layoutNode).getRectManager().m7366getOffsetFromRectListForBjo55l4(layoutNode);
                if (!IntOffset.m8277equalsimpl0(offsetFromRectList, IntOffset.INSTANCE.m8288getMaxnOccac())) {
                    return IntOffsetKt.m8293plusNvtHpc(position, offsetFromRectList);
                }
            }
            position = m7077toParentPosition8S9VItk$default(coordinator, position, false, 2, null);
        }
        return position;
    }

    protected final void withPositionTranslation(Canvas canvas, Function1<? super Canvas, Unit> block) {
        float x = IntOffset.m8278getXimpl(getPosition());
        float y = IntOffset.m8279getYimpl(getPosition());
        canvas.translate(x, y);
        block.invoke(canvas);
        canvas.translate(-x, -y);
    }

    /* JADX INFO: renamed from: toParentPosition-8S9VItk$default, reason: not valid java name */
    public static /* synthetic */ long m7077toParentPosition8S9VItk$default(NodeCoordinator nodeCoordinator, long j, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: toParentPosition-8S9VItk");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return nodeCoordinator.m7092toParentPosition8S9VItk(j, z);
    }

    /* JADX INFO: renamed from: toParentPosition-8S9VItk, reason: not valid java name */
    public long m7092toParentPosition8S9VItk(long position, boolean includeMotionFrameOfReference) {
        OwnedLayer layer = this.layer;
        long targetPosition = layer != null ? layer.mo7160mapOffset8S9VItk(position, false) : position;
        if (!includeMotionFrameOfReference && getIsPlacedUnderMotionFrameOfReference()) {
            return targetPosition;
        }
        return IntOffsetKt.m8293plusNvtHpc(targetPosition, getPosition());
    }

    /* JADX INFO: renamed from: fromParentPosition-8S9VItk$default, reason: not valid java name */
    public static /* synthetic */ long m7068fromParentPosition8S9VItk$default(NodeCoordinator nodeCoordinator, long j, boolean z, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fromParentPosition-8S9VItk");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return nodeCoordinator.m7083fromParentPosition8S9VItk(j, z);
    }

    /* JADX INFO: renamed from: fromParentPosition-8S9VItk, reason: not valid java name */
    public long m7083fromParentPosition8S9VItk(long position, boolean includeMotionFrameOfReference) {
        long relativeToPosition;
        if (!includeMotionFrameOfReference && getIsPlacedUnderMotionFrameOfReference()) {
            relativeToPosition = position;
        } else {
            relativeToPosition = IntOffsetKt.m8291minusNvtHpc(position, getPosition());
        }
        OwnedLayer layer = this.layer;
        return layer != null ? layer.mo7160mapOffset8S9VItk(relativeToPosition, true) : relativeToPosition;
    }

    protected final void drawBorder(Canvas canvas, Paint paint) {
        long arg0$iv = getMeasuredSize();
        long arg0$iv2 = getMeasuredSize();
        int $i$f$unpackInt2 = (int) (4294967295L & arg0$iv2);
        canvas.drawRect(0.5f, 0.5f, ((int) (arg0$iv >> 32)) - 0.5f, $i$f$unpackInt2 - 0.5f, paint);
    }

    public final void onLayoutNodeDetach() {
        releaseLayer();
        if (getLayoutNode().isPlaced()) {
            onUnplaced();
        }
    }

    public final void onRelease() {
        this.released = true;
        this.invalidateParentLayer.invoke();
        releaseLayer();
        if (!IntOffset.m8277equalsimpl0(getPosition(), IntOffset.INSTANCE.m8289getZeronOccac())) {
            getLayoutNode().onCoordinatorRectChanged$ui(this);
        }
    }

    public static /* synthetic */ void rectInParent$ui$default(NodeCoordinator nodeCoordinator, MutableRect mutableRect, boolean z, boolean z2, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: rectInParent");
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        nodeCoordinator.rectInParent$ui(mutableRect, z, z2);
    }

    public final void rectInParent$ui(MutableRect bounds, boolean clipBounds, boolean clipToMinimumTouchTargetSize) {
        OwnedLayer layer = this.layer;
        if (layer != null) {
            if (this.isClipping) {
                if (clipToMinimumTouchTargetSize) {
                    long minTouch = m7085getMinimumTouchTargetSizeNHjbRc();
                    long arg0$iv = m7080calculateMinimumTouchTargetOffsetC6jSQ5I(bounds, minTouch);
                    int bits$iv$iv$iv$iv = (int) (arg0$iv >> 32);
                    float left = Float.intBitsToFloat(bits$iv$iv$iv$iv);
                    int bits$iv$iv$iv$iv2 = (int) (arg0$iv & 4294967295L);
                    float top = Float.intBitsToFloat(bits$iv$iv$iv$iv2);
                    long arg0$iv2 = mo6791getSizeYbymL2g();
                    int width = (int) (arg0$iv2 >> 32);
                    int height = (int) (arg0$iv2 & 4294967295L);
                    int bits$iv$iv$iv = (int) (minTouch >> 32);
                    long arg0$iv3 = minTouch >> 32;
                    int bits$iv$iv$iv2 = (int) arg0$iv3;
                    float right = Math.min(width + Float.intBitsToFloat(bits$iv$iv$iv), Math.max(width, Float.intBitsToFloat(bits$iv$iv$iv2) + left));
                    int bits$iv$iv$iv3 = (int) (minTouch & 4294967295L);
                    long arg0$iv4 = minTouch & 4294967295L;
                    int bits$iv$iv$iv4 = (int) arg0$iv4;
                    float bottom = Math.min(height + Float.intBitsToFloat(bits$iv$iv$iv3), Math.max(height, Float.intBitsToFloat(bits$iv$iv$iv4) + top));
                    bounds.intersect(left, top, right, bottom);
                } else if (clipBounds) {
                    long arg0$iv5 = mo6791getSizeYbymL2g();
                    long arg0$iv6 = mo6791getSizeYbymL2g();
                    bounds.intersect(0.0f, 0.0f, (int) (arg0$iv5 >> 32), (int) (arg0$iv6 & 4294967295L));
                }
                if (bounds.isEmpty()) {
                    return;
                }
            }
            layer.mapBounds(bounds, false);
        }
        int x = IntOffset.m8278getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() + x);
        bounds.setRight(bounds.getRight() + x);
        int y = IntOffset.m8279getYimpl(getPosition());
        bounds.setTop(bounds.getTop() + y);
        bounds.setBottom(bounds.getBottom() + y);
    }

    private final void fromParentRect(MutableRect bounds, boolean clipBounds) {
        int x = IntOffset.m8278getXimpl(getPosition());
        bounds.setLeft(bounds.getLeft() - x);
        bounds.setRight(bounds.getRight() - x);
        int y = IntOffset.m8279getYimpl(getPosition());
        bounds.setTop(bounds.getTop() - y);
        bounds.setBottom(bounds.getBottom() - y);
        OwnedLayer layer = this.layer;
        if (layer != null) {
            layer.mapBounds(bounds, true);
            if (this.isClipping && clipBounds) {
                long arg0$iv = mo6791getSizeYbymL2g();
                long arg0$iv2 = mo6791getSizeYbymL2g();
                bounds.intersect(0.0f, 0.0f, (int) (arg0$iv >> 32), (int) (4294967295L & arg0$iv2));
                if (bounds.isEmpty()) {
                }
            }
        }
    }

    /* JADX INFO: renamed from: withinLayerBounds-k-4lQ0M, reason: not valid java name */
    protected final boolean m7094withinLayerBoundsk4lQ0M(long pointerPosition) {
        long v$iv = androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase ^ (pointerPosition & androidx.compose.ui.geometry.InlineClassHelperKt.DualFloatInfinityBase);
        if (!(((v$iv - androidx.compose.ui.geometry.InlineClassHelperKt.Uint64Low32) & (-9223372034707292160L)) == 0)) {
            return false;
        }
        OwnedLayer layer = this.layer;
        return layer == null || !this.isClipping || layer.mo7159isInLayerk4lQ0M(pointerPosition);
    }

    /* JADX INFO: renamed from: isPointerInBounds-k-4lQ0M, reason: not valid java name */
    protected final boolean m7088isPointerInBoundsk4lQ0M(long pointerPosition) {
        int bits$iv$iv$iv = (int) (pointerPosition >> 32);
        float x = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (4294967295L & pointerPosition);
        float y = Float.intBitsToFloat(bits$iv$iv$iv2);
        return x >= 0.0f && y >= 0.0f && x < ((float) getMeasuredWidth()) && y < ((float) getMeasuredHeight());
    }

    public void invalidateLayer() {
        OwnedLayer layer = this.layer;
        if (layer != null) {
            layer.invalidate();
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.invalidateLayer();
        }
    }

    public void onLayoutModifierNodeChanged() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
        }
    }

    public final NodeCoordinator findCommonAncestor$ui(NodeCoordinator other) {
        LayoutNode ancestor1 = other.getLayoutNode();
        LayoutNode ancestor2 = getLayoutNode();
        if (ancestor1 == ancestor2) {
            Modifier.Node otherNode = other.getTail();
            DelegatableNode $this$visitLocalAncestors$iv = getTail();
            int iM7100constructorimpl = NodeKind.m7100constructorimpl(2);
            boolean value$iv$iv = $this$visitLocalAncestors$iv.getNode().getIsAttached();
            if (!value$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
            }
            for (Modifier.Node next$iv = $this$visitLocalAncestors$iv.getNode().getParent(); next$iv != null; next$iv = next$iv.getParent()) {
                if ((next$iv.getKindSet() & iM7100constructorimpl) != 0) {
                    Modifier.Node it = next$iv;
                    if (it == otherNode) {
                        return other;
                    }
                }
            }
            return this;
        }
        while (ancestor1.getDepth() > ancestor2.getDepth()) {
            LayoutNode parent$ui = ancestor1.getParent$ui();
            Intrinsics.checkNotNull(parent$ui);
            ancestor1 = parent$ui;
        }
        while (ancestor2.getDepth() > ancestor1.getDepth()) {
            LayoutNode parent$ui2 = ancestor2.getParent$ui();
            Intrinsics.checkNotNull(parent$ui2);
            ancestor2 = parent$ui2;
        }
        while (ancestor1 != ancestor2) {
            LayoutNode parent1 = ancestor1.getParent$ui();
            LayoutNode parent2 = ancestor2.getParent$ui();
            if (parent1 == null || parent2 == null) {
                throw new IllegalArgumentException("layouts are not part of the same hierarchy");
            }
            ancestor1 = parent1;
            ancestor2 = parent2;
        }
        return ancestor2 == getLayoutNode() ? this : ancestor1 == other.getLayoutNode() ? other : ancestor1.getInnerCoordinator$ui();
    }

    public final boolean shouldSharePointerInputWithSiblings() {
        boolean dispatchAgain$iv$iv$iv;
        int type$iv;
        int type$iv2;
        int count$iv$iv$iv;
        MutableVector mutableVector;
        Modifier.Node start = headNode(NodeKindKt.m7109getIncludeSelfInTraversalH91voCI(NodeKind.m7100constructorimpl(16)));
        if (start != null && start.getIsAttached()) {
            Modifier.Node $this$visitSelfAndLocalDescendants_u2d6rFNWt0$iv = start;
            int type$iv3 = NodeKind.m7100constructorimpl(16);
            boolean value$iv$iv$iv = $this$visitSelfAndLocalDescendants_u2d6rFNWt0$iv.getNode().getIsAttached();
            if (!value$iv$iv$iv) {
                InlineClassHelperKt.throwIllegalStateException("visitLocalDescendants called on an unattached node");
            }
            Modifier.Node self$iv$iv = $this$visitSelfAndLocalDescendants_u2d6rFNWt0$iv.getNode();
            if ((self$iv$iv.getAggregateChildKindSet() & type$iv3) == 0) {
                return false;
            }
            Modifier.Node next$iv$iv = self$iv$iv;
            while (next$iv$iv != null) {
                if ((next$iv$iv.getKindSet() & type$iv3) != 0) {
                    Modifier.Node it$iv = next$iv$iv;
                    int kind$iv$iv = type$iv3;
                    MutableVector mutableVector2 = null;
                    Modifier.Node nodePop = it$iv;
                    while (nodePop != null) {
                        Modifier.Node start2 = start;
                        if (nodePop instanceof PointerInputModifierNode) {
                            PointerInputModifierNode it = (PointerInputModifierNode) nodePop;
                            if (it.sharePointerInputWithSiblings()) {
                                return true;
                            }
                            dispatchAgain$iv$iv$iv = false;
                        } else {
                            dispatchAgain$iv$iv$iv = true;
                        }
                        if (dispatchAgain$iv$iv$iv) {
                            Modifier.Node this_$iv$iv$iv$iv = nodePop;
                            int kind$iv$iv$iv$iv = (this_$iv$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                            if (kind$iv$iv$iv$iv != 0 && (nodePop instanceof DelegatingNode)) {
                                int count$iv$iv$iv2 = 0;
                                DelegatingNode this_$iv$iv$iv$iv2 = (DelegatingNode) nodePop;
                                Modifier.Node node$iv$iv$iv$iv = this_$iv$iv$iv$iv2.getDelegate();
                                while (node$iv$iv$iv$iv != null) {
                                    Modifier.Node next$iv$iv$iv = node$iv$iv$iv$iv;
                                    int kind$iv$iv$iv$iv2 = (next$iv$iv$iv.getKindSet() & kind$iv$iv) != 0 ? 1 : 0;
                                    if (kind$iv$iv$iv$iv2 != 0) {
                                        count$iv$iv$iv2++;
                                        type$iv2 = type$iv3;
                                        if (count$iv$iv$iv2 == 1) {
                                            nodePop = next$iv$iv$iv;
                                        } else {
                                            if (mutableVector2 == null) {
                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                            } else {
                                                count$iv$iv$iv = count$iv$iv$iv2;
                                                mutableVector = mutableVector2;
                                            }
                                            Modifier.Node theNode$iv$iv$iv = nodePop;
                                            if (theNode$iv$iv$iv != null) {
                                                if (mutableVector != null) {
                                                    mutableVector.add(theNode$iv$iv$iv);
                                                }
                                                nodePop = null;
                                            }
                                            if (mutableVector != null) {
                                                mutableVector.add(next$iv$iv$iv);
                                            }
                                            mutableVector2 = mutableVector;
                                            count$iv$iv$iv2 = count$iv$iv$iv;
                                        }
                                    } else {
                                        type$iv2 = type$iv3;
                                    }
                                    node$iv$iv$iv$iv = node$iv$iv$iv$iv.getChild();
                                    type$iv3 = type$iv2;
                                }
                                type$iv = type$iv3;
                                if (count$iv$iv$iv2 == 1) {
                                    start = start2;
                                    type$iv3 = type$iv;
                                } else {
                                    nodePop = DelegatableNodeKt.pop(mutableVector2);
                                    start = start2;
                                    type$iv3 = type$iv;
                                }
                            }
                        }
                        type$iv = type$iv3;
                        nodePop = DelegatableNodeKt.pop(mutableVector2);
                        start = start2;
                        type$iv3 = type$iv;
                    }
                }
                next$iv$iv = next$iv$iv.getChild();
                start = start;
                type$iv3 = type$iv3;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: renamed from: offsetFromEdge-MK-Hz9U, reason: not valid java name */
    private final long m7073offsetFromEdgeMKHz9U(long pointerPosition) {
        int bits$iv$iv$iv = (int) (pointerPosition >> 32);
        float x = Float.intBitsToFloat(bits$iv$iv$iv);
        float horizontal = Math.max(0.0f, x < 0.0f ? -x : x - getMeasuredWidth());
        int bits$iv$iv$iv2 = (int) (pointerPosition & 4294967295L);
        float y = Float.intBitsToFloat(bits$iv$iv$iv2);
        float vertical = Math.max(0.0f, y < 0.0f ? -y : y - getMeasuredHeight());
        long v1$iv$iv = Float.floatToRawIntBits(horizontal);
        long v2$iv$iv = Float.floatToRawIntBits(vertical);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: renamed from: calculateMinimumTouchTargetPadding-E7KxVPU, reason: not valid java name */
    protected final long m7081calculateMinimumTouchTargetPaddingE7KxVPU(long minimumTouchTargetSize) {
        int bits$iv$iv$iv = (int) (minimumTouchTargetSize >> 32);
        float widthDiff = Float.intBitsToFloat(bits$iv$iv$iv) - getMeasuredWidth();
        int bits$iv$iv$iv2 = (int) (minimumTouchTargetSize & 4294967295L);
        float heightDiff = Float.intBitsToFloat(bits$iv$iv$iv2) - getMeasuredHeight();
        float width$iv = Math.max(0.0f, widthDiff / 2.0f);
        float height$iv = Math.max(0.0f, heightDiff / 2.0f);
        long v1$iv$iv = Float.floatToRawIntBits(width$iv);
        long v2$iv$iv = Float.floatToRawIntBits(height$iv);
        return Size.m5128constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
    }

    /* JADX INFO: renamed from: calculateMinimumTouchTargetOffset-C6jSQ5I, reason: not valid java name */
    protected final long m7080calculateMinimumTouchTargetOffsetC6jSQ5I(MutableRect childRect, long minimumTouchTargetSize) {
        float left;
        float top;
        float childLeft = childRect.getLeft();
        float childTop = childRect.getTop();
        if (childRect.getRight() >= 0.0f) {
            long arg0$iv = mo6791getSizeYbymL2g();
            if (childLeft <= ((int) (arg0$iv >> 32)) && childRect.getBottom() >= 0.0f) {
                long arg0$iv2 = mo6791getSizeYbymL2g();
                if (childTop <= ((int) (arg0$iv2 & 4294967295L))) {
                    int bits$iv$iv$iv$iv = (int) (minimumTouchTargetSize >> 32);
                    float mttWidth = Float.intBitsToFloat(bits$iv$iv$iv$iv);
                    int bits$iv$iv$iv$iv2 = (int) (minimumTouchTargetSize & 4294967295L);
                    float mttHeight = Float.intBitsToFloat(bits$iv$iv$iv$iv2);
                    float underWidth = (mttWidth - (childRect.getRight() - childRect.getLeft())) / 2.0f;
                    if (underWidth > 0.0f) {
                        left = childLeft - underWidth;
                    } else {
                        left = RangesKt.coerceAtLeast(childLeft, (-mttWidth) / 2.0f);
                    }
                    float underHeight = (mttHeight - (childRect.getBottom() - childRect.getTop())) / 2.0f;
                    if (underHeight > 0.0f) {
                        top = childTop - underHeight;
                    } else {
                        top = RangesKt.coerceAtLeast(childTop, (-mttHeight) / 2.0f);
                    }
                    float y$iv = top;
                    float x$iv = left;
                    long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                    long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                    return Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
                }
            }
        }
        return Offset.INSTANCE.m5084getZeroF1C5BW0();
    }

    /* JADX INFO: renamed from: distanceInMinimumTouchTarget-tz77jQw, reason: not valid java name */
    protected final float m7082distanceInMinimumTouchTargettz77jQw(long pointerPosition, long minimumTouchTargetSize) {
        int bits$iv$iv$iv = (int) (minimumTouchTargetSize >> 32);
        if (getMeasuredWidth() >= Float.intBitsToFloat(bits$iv$iv$iv)) {
            int bits$iv$iv$iv2 = (int) (minimumTouchTargetSize & 4294967295L);
            if (getMeasuredHeight() >= Float.intBitsToFloat(bits$iv$iv$iv2)) {
                return Float.POSITIVE_INFINITY;
            }
        }
        long arg0$iv = m7081calculateMinimumTouchTargetPaddingE7KxVPU(minimumTouchTargetSize);
        int bits$iv$iv$iv$iv = (int) (arg0$iv >> 32);
        float width = Float.intBitsToFloat(bits$iv$iv$iv$iv);
        int bits$iv$iv$iv$iv2 = (int) (arg0$iv & 4294967295L);
        float height = Float.intBitsToFloat(bits$iv$iv$iv$iv2);
        long offsetFromEdge = m7073offsetFromEdgeMKHz9U(pointerPosition);
        if (width > 0.0f || height > 0.0f) {
            int bits$iv$iv$iv3 = (int) (offsetFromEdge >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv3) <= width) {
                int bits$iv$iv$iv4 = (int) (offsetFromEdge & 4294967295L);
                if (Float.intBitsToFloat(bits$iv$iv$iv4) <= height) {
                    return Offset.m5067getDistanceSquaredimpl(offsetFromEdge);
                }
            }
        }
        return Float.POSITIVE_INFINITY;
    }

    /* JADX INFO: compiled from: NodeCoordinator.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b`\u0018\u00002\u00020\u0001J\u0013\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H&¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH&J7\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0007H&¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\fH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001cÀ\u0006\u0001"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "", "entityType", "Landroidx/compose/ui/node/NodeKind;", "entityType-OLwlOKw", "()I", "interceptOutOfBoundsChildEvents", "", "node", "Landroidx/compose/ui/Modifier$Node;", "shouldHitTestChildren", "parentLayoutNode", "Landroidx/compose/ui/node/LayoutNode;", "childHitTest", "", "layoutNode", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "hitTestResult", "Landroidx/compose/ui/node/HitTestResult;", "pointerType", "Landroidx/compose/ui/input/pointer/PointerType;", "isInLayer", "childHitTest-qzLsGqo", "(Landroidx/compose/ui/node/LayoutNode;JLandroidx/compose/ui/node/HitTestResult;IZ)V", "shouldHitTest", "shareWithSiblings", "child", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface HitTestSource {
        /* JADX INFO: renamed from: childHitTest-qzLsGqo */
        void mo7095childHitTestqzLsGqo(LayoutNode layoutNode, long pointerPosition, HitTestResult hitTestResult, int pointerType, boolean isInLayer);

        /* JADX INFO: renamed from: entityType-OLwlOKw */
        int mo7096entityTypeOLwlOKw();

        boolean interceptOutOfBoundsChildEvents(Modifier.Node node);

        boolean shareWithSiblings(HitTestResult hitTestResult, LayoutNode child);

        boolean shouldHitTestChildren(LayoutNode parentLayoutNode);

        default boolean shouldHitTest(Modifier.Node node) {
            return true;
        }
    }

    /* JADX INFO: compiled from: NodeCoordinator.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/node/NodeCoordinator$Companion;", "", "<init>", "()V", "ExpectAttachedLayoutCoordinates", "", "UnmeasuredError", "onCommitAffectingLayerParams", "Lkotlin/Function1;", "Landroidx/compose/ui/node/NodeCoordinator;", "", "onCommitAffectingLayer", "graphicsLayerScope", "Landroidx/compose/ui/graphics/ReusableGraphicsLayerScope;", "tmpLayerPositionalProperties", "Landroidx/compose/ui/node/LayerPositionalProperties;", "tmpMatrix", "Landroidx/compose/ui/graphics/Matrix;", "[F", "PointerInputSource", "Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "getPointerInputSource", "()Landroidx/compose/ui/node/NodeCoordinator$HitTestSource;", "SemanticsSource", "getSemanticsSource", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HitTestSource getPointerInputSource() {
            return NodeCoordinator.PointerInputSource;
        }

        public final HitTestSource getSemanticsSource() {
            return NodeCoordinator.SemanticsSource;
        }
    }
}
