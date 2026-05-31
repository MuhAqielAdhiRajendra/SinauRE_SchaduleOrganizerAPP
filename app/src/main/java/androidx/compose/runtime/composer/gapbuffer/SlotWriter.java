package androidx.compose.runtime.composer.gapbuffer;

import androidx.autofill.HintConstants;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectList;
import androidx.collection.ObjectList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.GapComposerKt;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0001\u0018\u0000 Û\u00012\u00020\u0001:\u0002Û\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u00103\u001a\u00020/2\u0006\u00108\u001a\u00020\u0019J\u000e\u0010!\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\u000e\u00109\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\u0010\u0010:\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u0019J\u000e\u0010;\u001a\u00020/2\u0006\u00108\u001a\u00020\u0019J\u000e\u0010<\u001a\u00020/2\u0006\u00108\u001a\u00020\u0019J\u000e\u0010=\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\u0010\u0010>\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u0019J\u000e\u0010?\u001a\u00020/2\u0006\u00108\u001a\u00020\u0019J\u000e\u0010@\u001a\u00020/2\u0006\u00108\u001a\u00020\u0019J\u0016\u0010A\u001a\u00020/2\u0006\u00108\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u0019J\u0010\u0010C\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u0019J\u0010\u0010C\u001a\u0004\u0018\u00010\u00012\u0006\u0010D\u001a\u00020\u000fJ\u000e\u0010E\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\u000e\u0010E\u001a\u00020\u00192\u0006\u0010D\u001a\u00020\u000fJ\u000e\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020/J\u0006\u0010L\u001a\u00020JJ\u0012\u0010M\u001a\u0004\u0018\u00010\u00012\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0014\u0010N\u001a\u0004\u0018\u00010\u00012\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0002J\u0018\u0010O\u001a\u00020J2\u0006\u0010D\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u000e\u0010P\u001a\u00020J2\u0006\u0010Q\u001a\u00020\u0019J\u0010\u0010R\u001a\u00020J2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0010\u0010S\u001a\u00020J2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0006\u0010T\u001a\u00020JJ\u000e\u0010U\u001a\u00020J2\u0006\u0010V\u001a\u00020WJ\u0016\u0010X\u001a\u00020J2\u0006\u0010Y\u001a\u00020\u00192\u0006\u0010(\u001a\u00020WJ\u0006\u0010Z\u001a\u00020JJ\u001c\u0010[\u001a\u0004\u0018\u00010\u00132\u0006\u0010E\u001a\u00020\u00192\b\u0010V\u001a\u0004\u0018\u00010WH\u0002J\u0010\u0010\\\u001a\u00020J2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0018\u0010\\\u001a\u00020J2\u0006\u0010D\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0010\u0010]\u001a\u00020J2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0010\u0010^\u001a\u00020J2\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u001d\u0010^\u001a\u0004\u0018\u00010\u00012\u0006\u00108\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0086\bJ\u0016\u0010_\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J\"\u0010^\u001a\u0004\u0018\u00010\u00012\u0006\u0010B\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u0001J\u0010\u0010`\u001a\u0004\u0018\u00010\u00012\u0006\u0010a\u001a\u00020\u0019J\b\u0010b\u001a\u0004\u0018\u00010\u0001J\u0018\u0010c\u001a\u0004\u0018\u00010\u00012\u0006\u0010D\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u0019J\u0018\u0010c\u001a\u0004\u0018\u00010\u00012\u0006\u0010d\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019J5\u0010e\u001a\u00020J2\u0006\u0010d\u001a\u00020\u00192\u0006\u0010Q\u001a\u00020\u00192\u001a\u0010f\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020J0gH\u0086\bJ\u0015\u0010h\u001a\u00020\u00192\u0006\u0010d\u001a\u00020\u0019H\u0000¢\u0006\u0002\biJ\u0015\u0010j\u001a\u00020\u00192\u0006\u0010d\u001a\u00020\u0019H\u0000¢\u0006\u0002\bkJ\u0015\u0010l\u001a\u00020\u00192\u0006\u0010d\u001a\u00020\u0019H\u0000¢\u0006\u0002\bmJ\u000e\u0010p\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u0019J\u000e\u0010q\u001a\u00020J2\u0006\u0010r\u001a\u00020\u0019J\u000e\u0010s\u001a\u00020J2\u0006\u0010D\u001a\u00020\u000fJ\u0006\u0010t\u001a\u00020JJ\u0006\u0010u\u001a\u00020JJ\u0006\u0010v\u001a\u00020JJ\u0006\u0010w\u001a\u00020JJ\u000e\u0010w\u001a\u00020J2\u0006\u0010Y\u001a\u00020\u0019J\u0018\u0010w\u001a\u00020J2\u0006\u0010Y\u001a\u00020\u00192\b\u0010x\u001a\u0004\u0018\u00010\u0001J\u0018\u0010y\u001a\u00020J2\u0006\u0010Y\u001a\u00020\u00192\b\u0010z\u001a\u0004\u0018\u00010\u0001J\"\u0010y\u001a\u00020J2\u0006\u0010Y\u001a\u00020\u00192\b\u0010z\u001a\u0004\u0018\u00010\u00012\b\u0010C\u001a\u0004\u0018\u00010\u0001J\"\u0010{\u001a\u00020J2\u0006\u0010Y\u001a\u00020\u00192\b\u0010z\u001a\u0004\u0018\u00010\u00012\b\u0010|\u001a\u0004\u0018\u00010\u0001J\u0018\u0010{\u001a\u00020J2\u0006\u0010Y\u001a\u00020\u00192\b\u0010|\u001a\u0004\u0018\u00010\u0001J,\u0010w\u001a\u00020J2\u0006\u0010Y\u001a\u00020\u00192\b\u0010z\u001a\u0004\u0018\u00010\u00012\u0006\u00103\u001a\u00020/2\b\u0010|\u001a\u0004\u0018\u00010\u0001H\u0002J\u0006\u0010}\u001a\u00020\u0019J\u000e\u0010~\u001a\u00020J2\u0006\u00108\u001a\u00020\u0019J\u000e\u0010~\u001a\u00020J2\u0006\u0010D\u001a\u00020\u000fJ\u0006\u0010\u007f\u001a\u00020\u0019J\u0007\u0010\u0080\u0001\u001a\u00020/J\u0010\u0010\u0081\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0082\u0001JQ\u0010\u0083\u0001\u001a\u00020J2\u0006\u0010B\u001a\u00020\u00192=\u0010f\u001a9\u0012\u0015\u0012\u00130\u0019¢\u0006\u000e\b\u0084\u0001\u0012\t\b\u0085\u0001\u0012\u0004\b\b(8\u0012\u0018\u0012\u0016\u0018\u00010\u0001¢\u0006\u000f\b\u0084\u0001\u0012\n\b\u0085\u0001\u0012\u0005\b\b(\u0086\u0001\u0012\u0004\u0012\u00020J0gH\u0086\bJb\u0010\u0087\u0001\u001a\u00020J2\u0006\u0010B\u001a\u00020\u00192&\u0010\u0088\u0001\u001a!\u0012\u0016\u0012\u00140\u0019¢\u0006\u000f\b\u0084\u0001\u0012\n\b\u0085\u0001\u0012\u0005\b\b(\u008a\u0001\u0012\u0004\u0012\u00020J0\u0089\u00012&\u0010\u008b\u0001\u001a!\u0012\u0016\u0012\u00140\u0019¢\u0006\u000f\b\u0084\u0001\u0012\n\b\u0085\u0001\u0012\u0005\b\b(\u008a\u0001\u0012\u0004\u0012\u00020J0\u0089\u0001H\u0086\bJN\u0010\u008c\u0001\u001a\u00020J2\u0006\u0010B\u001a\u00020\u00192=\u0010f\u001a9\u0012\u0015\u0012\u00130\u0019¢\u0006\u000e\b\u0084\u0001\u0012\t\b\u0085\u0001\u0012\u0004\b\b(8\u0012\u0018\u0012\u0016\u0018\u00010\u0001¢\u0006\u000f\b\u0084\u0001\u0012\n\b\u0085\u0001\u0012\u0005\b\b(\u0086\u0001\u0012\u0004\u0012\u00020J0gJ\u0019\u0010\u008d\u0001\u001a\u00020\u00192\u0006\u0010E\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002J\u0010\u0010\u008e\u0001\u001a\u00020J2\u0007\u0010\u008f\u0001\u001a\u00020\u0019J\u0018\u0010\u0090\u0001\u001a\u00020/2\u0007\u0010\u0091\u0001\u001a\u00020\u000f2\u0006\u0010D\u001a\u00020\u000fJ(\u0010\u0092\u0001\u001a\t\u0012\u0004\u0012\u00020\u000f0\u0093\u00012\u0006\u0010D\u001a\u00020\u000f2\u0007\u0010\u008f\u0001\u001a\u00020\u00192\u0007\u0010\u0094\u0001\u001a\u00020\u0000J)\u0010\u0095\u0001\u001a\t\u0012\u0004\u0012\u00020\u000f0\u0093\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00192\t\b\u0002\u0010\u0096\u0001\u001a\u00020/J\u0007\u0010\u0097\u0001\u001a\u00020JJ'\u0010\u0098\u0001\u001a\t\u0012\u0004\u0012\u00020\u000f0\u0093\u00012\u0007\u0010\u008f\u0001\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u0019J\u0010\u0010D\u001a\u00020\u000f2\b\b\u0002\u00108\u001a\u00020\u0019J\u0011\u0010\u0099\u0001\u001a\u00020J2\b\b\u0002\u0010B\u001a\u00020\u0019J\u0011\u0010\u009a\u0001\u001a\u00020/2\u0006\u0010B\u001a\u00020\u0019H\u0002J\u0011\u0010\u009b\u0001\u001a\u00020/2\u0006\u0010B\u001a\u00020\u0019H\u0002J\t\u0010\u009f\u0001\u001a\u00020JH\u0002J\u0011\u0010 \u0001\u001a\u00020J2\u0006\u0010B\u001a\u00020\u0019H\u0002J#\u0010¡\u0001\u001a\u00020J2\u0006\u0010B\u001a\u00020\u00192\u0007\u0010^\u001a\u00030\u009d\u0001H\u0002¢\u0006\u0006\b¢\u0001\u0010£\u0001J\u0011\u0010¤\u0001\u001a\u00020/2\u0006\u0010B\u001a\u00020\u0019H\u0002J\u000f\u0010¥\u0001\u001a\u00020\u00192\u0006\u0010D\u001a\u00020\u000fJ\t\u0010¦\u0001\u001a\u00020WH\u0016J\t\u0010§\u0001\u001a\u00020JH\u0002J\t\u0010¨\u0001\u001a\u00020\u0019H\u0002J\"\u0010©\u0001\u001a\u00020J2\u0006\u0010E\u001a\u00020\u00192\u0006\u0010}\u001a\u00020\u00192\u0007\u0010ª\u0001\u001a\u00020\u0019H\u0002J\u0011\u0010«\u0001\u001a\u00020J2\u0006\u00108\u001a\u00020\u0019H\u0002J\u0019\u0010¬\u0001\u001a\u00020J2\u0006\u00108\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u0019H\u0002J\t\u0010\u00ad\u0001\u001a\u00020JH\u0002J\u0012\u0010®\u0001\u001a\u00020J2\u0007\u0010¯\u0001\u001a\u00020\u0019H\u0002J\u001a\u0010°\u0001\u001a\u00020J2\u0007\u0010¯\u0001\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u0019H\u0002J\u001b\u0010±\u0001\u001a\u00020/2\u0007\u0010²\u0001\u001a\u00020\u00192\u0007\u0010³\u0001\u001a\u00020\u0019H\u0002J\u0019\u0010´\u0001\u001a\u0004\u0018\u00010\u00132\u0006\u0010B\u001a\u00020\u0019H\u0000¢\u0006\u0003\bµ\u0001J\u0019\u0010¶\u0001\u001a\u0004\u0018\u00010\u000f2\u0006\u0010B\u001a\u00020\u0019H\u0000¢\u0006\u0003\b·\u0001J#\u0010¸\u0001\u001a\u00020J2\u0007\u0010²\u0001\u001a\u00020\u00192\u0007\u0010³\u0001\u001a\u00020\u00192\u0006\u0010B\u001a\u00020\u0019H\u0002J\u001b\u0010¹\u0001\u001a\u00020J2\u0006\u00108\u001a\u00020\u00192\b\u0010(\u001a\u0004\u0018\u00010\u0001H\u0002J\u001b\u0010º\u0001\u001a\u00020J2\u0007\u0010»\u0001\u001a\u00020\u00192\u0007\u0010¼\u0001\u001a\u00020\u0019H\u0002JC\u0010½\u0001\u001a\u00020/2\u0007\u0010¾\u0001\u001a\u00020\u00192\u0007\u0010¯\u0001\u001a\u00020\u00192&\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012j\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u0014H\u0002J$\u0010¿\u0001\u001a\u00020J2\u0007\u0010À\u0001\u001a\u00020\u00192\u0007\u0010Á\u0001\u001a\u00020\u00192\u0007\u0010¯\u0001\u001a\u00020\u0019H\u0002J\u0007\u0010Â\u0001\u001a\u00020WJ\u001b\u0010Ã\u0001\u001a\u00020J*\b0Ä\u0001j\u0003`Å\u00012\u0006\u00108\u001a\u00020\u0019H\u0002J\u000f\u0010Æ\u0001\u001a\u00020JH\u0000¢\u0006\u0003\bÇ\u0001J\u000f\u0010È\u0001\u001a\u00020JH\u0000¢\u0006\u0003\bÉ\u0001J\u0011\u0010Í\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002J\u0012\u0010Î\u0001\u001a\u00020\u00192\u0007\u0010Ï\u0001\u001a\u00020\u0019H\u0002J\u0014\u0010E\u001a\u00020\u0019*\u00020\t2\u0006\u00108\u001a\u00020\u0019H\u0002J\u0011\u0010Ï\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002J\u0016\u0010Ï\u0001\u001a\u00020\u0019*\u00020\t2\u0007\u0010Ð\u0001\u001a\u00020\u0019H\u0002J\u0015\u0010a\u001a\u00020\u0019*\u00020\t2\u0007\u0010Ð\u0001\u001a\u00020\u0019H\u0002J\u001f\u0010Ñ\u0001\u001a\u00020J*\u00020\t2\u0007\u0010Ð\u0001\u001a\u00020\u00192\u0007\u0010Ï\u0001\u001a\u00020\u0019H\u0002J\u0016\u0010Ò\u0001\u001a\u00020\u0019*\u00020\t2\u0007\u0010Ð\u0001\u001a\u00020\u0019H\u0002J\u0016\u0010Ó\u0001\u001a\u00020\u0019*\u00020\t2\u0007\u0010Ð\u0001\u001a\u00020\u0019H\u0002J\u0014\u0010Ô\u0001\u001a\t\u0012\u0004\u0012\u00020\u00190\u0093\u0001*\u00020\tH\u0002J\u0010\u0010Õ\u0001\u001a\t\u0012\u0004\u0012\u00020\u00190\u0093\u0001H\u0002J,\u0010Ö\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u00192\u0007\u0010¾\u0001\u001a\u00020\u00192\u0007\u0010×\u0001\u001a\u00020\u00192\u0007\u0010Ë\u0001\u001a\u00020\u0019H\u0002J#\u0010Ø\u0001\u001a\u00020\u00192\u0006\u0010D\u001a\u00020\u00192\u0007\u0010×\u0001\u001a\u00020\u00192\u0007\u0010Ë\u0001\u001a\u00020\u0019H\u0002J\u001a\u0010Ù\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u00192\u0007\u0010¾\u0001\u001a\u00020\u0019H\u0002J\u0011\u0010Ú\u0001\u001a\u00020\u00192\u0006\u00108\u001a\u00020\u0019H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000f0\u000ej\b\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R.\u0010\u0011\u001a\"\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012j\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0013\u0018\u0001`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010'\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010)\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001e\u0010,\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b-\u0010+R\u0011\u0010.\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b.\u00100R\u0011\u00101\u001a\u00020\u00198F¢\u0006\u0006\u001a\u0004\b2\u0010+R\u0011\u00103\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b3\u00100R\u0011\u00104\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b5\u00100R\u0011\u00106\u001a\u00020/8F¢\u0006\u0006\u001a\u0004\b7\u00100R\u001e\u0010E\u001a\u00020\u00192\u0006\u0010(\u001a\u00020\u0019@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bF\u0010+R\u001e\u0010G\u001a\u00020/2\u0006\u0010(\u001a\u00020/@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\bH\u00100R\u0014\u0010n\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bo\u0010+R\u0015\u0010\u009c\u0001\u001a\u0005\u0018\u00010\u009d\u0001X\u0082\u000e¢\u0006\u0005\n\u0003\u0010\u009e\u0001R\u0016\u0010¯\u0001\u001a\u00020\u00198@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bÊ\u0001\u0010+R\u0016\u0010Ë\u0001\u001a\u00020\u00198BX\u0082\u0004¢\u0006\u0007\u001a\u0005\bÌ\u0001\u0010+¨\u0006Ü\u0001"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "", "table", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "<init>", "(Landroidx/compose/runtime/composer/gapbuffer/SlotTable;)V", "getTable$runtime", "()Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "groups", "", "slots", "", "[Ljava/lang/Object;", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "Lkotlin/collections/ArrayList;", "sourceInformationMap", "Ljava/util/HashMap;", "Landroidx/compose/runtime/composer/gapbuffer/GapGroupSourceInformation;", "Lkotlin/collections/HashMap;", "calledByMap", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableIntSet;", "groupGapStart", "", "groupGapLen", "currentSlot", "currentSlotEnd", "slotsGapStart", "slotsGapLen", "slotsGapOwner", "insertCount", "nodeCount", "startStack", "Landroidx/compose/runtime/IntStack;", "endStack", "nodeCountStack", "deferredSlotWrites", "Landroidx/collection/MutableObjectList;", "value", "currentGroup", "getCurrentGroup", "()I", "currentGroupEnd", "getCurrentGroupEnd", "isGroupEnd", "", "()Z", "slotsSize", "getSlotsSize", "isNode", "collectingSourceInformation", "getCollectingSourceInformation", "collectingCalledInformation", "getCollectingCalledInformation", "index", "groupKey", "groupObjectKey", "isValid", "hasObjectKey", "groupSize", "groupAux", "indexInParent", "indexInCurrentGroup", "indexInGroup", "group", "node", "anchor", "parent", "getParent", "closed", "getClosed", "close", "", "normalClose", "reset", "update", "rawUpdate", "appendSlot", "trimTailSlots", "count", "updateAux", "insertAux", "updateToTableMaps", "recordGroupSourceInformation", "sourceInformation", "", "recordGrouplessCallSourceInformationStart", "key", "recordGrouplessCallSourceInformationEnd", "groupSourceInformationFor", "updateNode", "updateParentNode", "set", "slotIndexOfGroupSlotIndex", "clear", "slotIndex", "skip", "slot", "groupIndex", "forEachTailSlot", "block", "Lkotlin/Function2;", "slotsStartIndex", "slotsStartIndex$runtime", "slotsEndIndex", "slotsEndIndex$runtime", "slotsEndAllIndex", "slotsEndAllIndex$runtime", "currentGroupSlotIndex", "getCurrentGroupSlotIndex", "groupSlotIndex", "advanceBy", "amount", "seek", "skipToGroupEnd", "beginInsert", "endInsert", "startGroup", "dataKey", "startNode", "objectKey", "startData", "aux", "endGroup", "ensureStarted", "skipGroup", "removeGroup", "groupSlots", "", "forAllData", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "data", "traverseGroupAndChildren", "enter", "Lkotlin/Function1;", "child", "exit", "forAllDataInRememberOrder", "childGroupAtIndex", "moveGroup", TypedValues.CycleType.S_WAVE_OFFSET, "inGroup", "groupAnchor", "moveTo", "", "writer", "moveFrom", "removeSourceGroup", "bashCurrentGroup", "moveIntoGroupFrom", "markGroup", "containsGroupMark", "containsAnyGroupMarks", "pendingRecalculateMarks", "Landroidx/compose/runtime/composer/gapbuffer/PrioritySet;", "Landroidx/collection/MutableIntList;", "recalculateMarks", "updateContainsMark", "updateContainsMarkNow", "updateContainsMarkNow-qrM0pCk", "(ILandroidx/collection/MutableIntList;)V", "childContainsAnyMarks", "anchorIndex", "toString", "saveCurrentGroupEnd", "restoreCurrentGroupEnd", "fixParentAnchorsFor", "firstChild", "moveGroupGapTo", "moveSlotGapTo", "clearSlotGap", "insertGroups", "size", "insertSlots", "removeGroups", "start", "len", "sourceInformationOf", "sourceInformationOf$runtime", "tryAnchor", "tryAnchor$runtime", "removeSlots", "updateNodeOfGroup", "updateAnchors", "previousGapStart", "newGapStart", "removeAnchors", "gapStart", "moveAnchors", "originalLocation", "newLocation", "toDebugString", "groupAsString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "verifyDataAnchors", "verifyDataAnchors$runtime", "verifyParentAnchors", "verifyParentAnchors$runtime", "getSize$runtime", "capacity", "getCapacity", "groupIndexToAddress", "dataIndexToDataAddress", "dataIndex", "address", "updateDataIndex", "nodeIndex", "auxIndex", "dataIndexes", "keys", "dataIndexToDataAnchor", "gapLen", "dataAnchorToDataIndex", "parentIndexToAnchor", "parentAnchorToIndex", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class SlotWriter {
    private ArrayList<GapAnchor> anchors;
    private MutableIntObjectMap<MutableIntSet> calledByMap;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private MutableIntObjectMap<MutableObjectList<Object>> deferredSlotWrites;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private MutableIntList pendingRecalculateMarks;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private HashMap<GapAnchor, GapGroupSourceInformation> sourceInformationMap;
    private final SlotTable table;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private final IntStack startStack = new IntStack();
    private final IntStack endStack = new IntStack();
    private final IntStack nodeCountStack = new IntStack();
    private int parent = -1;

    public SlotWriter(SlotTable table) {
        this.table = table;
        this.groups = this.table.getGroups();
        this.slots = this.table.getSlots();
        this.anchors = this.table.getAnchors$runtime();
        this.sourceInformationMap = this.table.getSourceInformationMap$runtime();
        this.calledByMap = this.table.getCalledByMap$runtime();
        this.groupGapStart = this.table.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - this.table.getGroupsSize();
        this.slotsGapStart = this.table.getSlotsSize();
        this.slotsGapLen = this.slots.length - this.table.getSlotsSize();
        this.slotsGapOwner = this.table.getGroupsSize();
        this.currentGroupEnd = this.table.getGroupsSize();
    }

    /* JADX INFO: renamed from: getTable$runtime, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final int getCurrentGroupEnd() {
        return this.currentGroupEnd;
    }

    public final boolean isGroupEnd() {
        return this.currentGroup == this.currentGroupEnd;
    }

    public final int getSlotsSize() {
        return this.slots.length - this.slotsGapLen;
    }

    public final boolean isNode() {
        if (this.currentGroup >= this.currentGroupEnd) {
            return false;
        }
        int[] $this$isNode$iv = this.groups;
        int address$iv = groupIndexToAddress(this.currentGroup);
        return ($this$isNode$iv[(address$iv * 5) + 1] & 1073741824) != 0;
    }

    public final boolean getCollectingSourceInformation() {
        return this.sourceInformationMap != null;
    }

    public final boolean getCollectingCalledInformation() {
        return this.calledByMap != null;
    }

    public final boolean isNode(int index) {
        int[] $this$isNode$iv = this.groups;
        int address$iv = groupIndexToAddress(index);
        return ($this$isNode$iv[(address$iv * 5) + 1] & 1073741824) != 0;
    }

    public final int nodeCount(int index) {
        int[] $this$nodeCount$iv = this.groups;
        int address$iv = groupIndexToAddress(index);
        return $this$nodeCount$iv[(address$iv * 5) + 1] & 67108863;
    }

    public final int groupKey(int index) {
        int[] $this$key$iv = this.groups;
        int address$iv = groupIndexToAddress(index);
        return $this$key$iv[address$iv * 5];
    }

    public final Object groupObjectKey(int index) {
        int address = groupIndexToAddress(index);
        int[] $this$hasObjectKey$iv = this.groups;
        if (($this$hasObjectKey$iv[(address * 5) + 1] & GroupFlagsKt.HasMovableContentFlag) != 0) {
            return this.slots[SlotTableKt.objectKeyIndex(this.groups, address)];
        }
        return null;
    }

    public final boolean isValid(int index) {
        return groupIndexToAddress(index) * 5 < this.groups.length;
    }

    public final boolean hasObjectKey(int index) {
        int address = groupIndexToAddress(index);
        int[] $this$hasObjectKey$iv = this.groups;
        return ($this$hasObjectKey$iv[(address * 5) + 1] & GroupFlagsKt.HasMovableContentFlag) != 0;
    }

    public final int groupSize(int index) {
        return SlotTableKt.groupSize(this.groups, groupIndexToAddress(index));
    }

    public final Object groupAux(int index) {
        int address = groupIndexToAddress(index);
        int[] $this$hasAux$iv = this.groups;
        return ($this$hasAux$iv[(address * 5) + 1] & GroupFlagsKt.IsMovableContentFlag) != 0 ? this.slots[auxIndex(this.groups, address)] : Composer.INSTANCE.getEmpty();
    }

    public final boolean indexInParent(int index) {
        return (index > this.parent && index < this.currentGroupEnd) || (this.parent == 0 && index == 0);
    }

    public final boolean indexInCurrentGroup(int index) {
        return indexInGroup(index, this.currentGroup);
    }

    public final boolean indexInGroup(int index, int group) {
        int openIndex;
        int end;
        if (group == this.parent) {
            end = this.currentGroupEnd;
        } else {
            end = (group <= this.startStack.peekOr(0) && (openIndex = this.startStack.indexOf(group)) >= 0) ? (getCapacity() - this.groupGapLen) - this.endStack.peek(openIndex) : groupSize(group) + group;
        }
        return index > group && index < end;
    }

    public final Object node(int index) {
        int address = groupIndexToAddress(index);
        int[] $this$isNode$iv = this.groups;
        if (($this$isNode$iv[(address * 5) + 1] & 1073741824) != 0) {
            return this.slots[dataIndexToDataAddress(nodeIndex(this.groups, address))];
        }
        return null;
    }

    public final Object node(GapAnchor anchor) {
        return node(anchor.toIndexFor(this));
    }

    public final int getParent() {
        return this.parent;
    }

    public final int parent(int index) {
        return parent(this.groups, index);
    }

    public final int parent(GapAnchor anchor) {
        if (anchor.getValid()) {
            return parent(this.groups, anchorIndex(anchor));
        }
        return -1;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final void close(boolean normalClose) {
        this.closed = true;
        if (normalClose) {
            IntStack this_$iv = this.startStack;
            if (this_$iv.tos == 0) {
                moveGroupGapTo(getSize$runtime());
                moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
                clearSlotGap();
                recalculateMarks();
            }
        }
        this.table.close$runtime(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors, this.sourceInformationMap, this.calledByMap);
    }

    public final void reset() {
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot reset when inserting");
        }
        recalculateMarks();
        this.currentGroup = 0;
        this.currentGroupEnd = getCapacity() - this.groupGapLen;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
        this.nodeCount = 0;
    }

    public final Object update(Object value) {
        if (this.insertCount > 0 && this.currentSlot != this.slotsGapStart) {
            MutableIntObjectMap<MutableObjectList<Object>> mutableIntObjectMap = this.deferredSlotWrites;
            DefaultConstructorMarker defaultConstructorMarker = null;
            int i = 1;
            int i2 = 0;
            if (mutableIntObjectMap == null) {
                mutableIntObjectMap = new MutableIntObjectMap<>(i2, i, defaultConstructorMarker);
            }
            this.deferredSlotWrites = mutableIntObjectMap;
            int key$iv = this.parent;
            MutableObjectList<Object> mutableObjectList = mutableIntObjectMap.get(key$iv);
            if (mutableObjectList == null) {
                MutableObjectList<Object> mutableObjectList2 = new MutableObjectList<>(i2, i, defaultConstructorMarker);
                mutableIntObjectMap.set(key$iv, mutableObjectList2);
                mutableObjectList = mutableObjectList2;
            }
            mutableObjectList.add(value);
            return Composer.INSTANCE.getEmpty();
        }
        return rawUpdate(value);
    }

    private final Object rawUpdate(Object value) {
        Object result = skip();
        set(value);
        return result;
    }

    public final void appendSlot(GapAnchor anchor, Object value) {
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Can only append a slot if not current inserting");
        }
        int previousCurrentSlot = this.currentSlot;
        int previousCurrentSlotEnd = this.currentSlotEnd;
        int anchorIndex = anchorIndex(anchor);
        int slotIndex = dataIndex(this.groups, groupIndexToAddress(anchorIndex + 1));
        this.currentSlot = slotIndex;
        this.currentSlotEnd = slotIndex;
        insertSlots(1, anchorIndex);
        if (previousCurrentSlot >= slotIndex) {
            previousCurrentSlot++;
            previousCurrentSlotEnd++;
        }
        this.slots[slotIndex] = value;
        this.currentSlot = previousCurrentSlot;
        this.currentSlotEnd = previousCurrentSlotEnd;
    }

    public final void trimTailSlots(int count) {
        boolean value$iv = count > 0;
        boolean value$iv$iv = value$iv;
        if (!value$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        int parent = this.parent;
        int groupSlotStart = slotIndex(this.groups, groupIndexToAddress(parent));
        int groupSlotEnd = dataIndex(this.groups, groupIndexToAddress(parent + 1));
        int removeStart = groupSlotEnd - count;
        boolean value$iv2 = removeStart >= groupSlotStart;
        boolean value$iv$iv2 = value$iv2;
        if (!value$iv$iv2) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        removeSlots(removeStart, count, parent);
        int currentSlot = this.currentSlot;
        if (currentSlot >= groupSlotStart) {
            this.currentSlot = currentSlot - count;
        }
    }

    public final void updateAux(Object value) {
        int address = groupIndexToAddress(this.currentGroup);
        int[] $this$hasAux$iv = this.groups;
        boolean value$iv = ($this$hasAux$iv[(address * 5) + 1] & GroupFlagsKt.IsMovableContentFlag) != 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Updating the data of a group that was not created with a data slot");
        }
        this.slots[dataIndexToDataAddress(auxIndex(this.groups, address))] = value;
    }

    public final void insertAux(Object value) {
        boolean value$iv = this.insertCount >= 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot insert auxiliary data when not inserting");
        }
        int parent = this.parent;
        int parentGroupAddress = groupIndexToAddress(parent);
        int[] $this$hasAux$iv = this.groups;
        boolean value$iv2 = !(($this$hasAux$iv[(parentGroupAddress * 5) + 1] & GroupFlagsKt.IsMovableContentFlag) != 0);
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Group already has auxiliary data");
        }
        insertSlots(1, parent);
        int auxIndex = auxIndex(this.groups, parentGroupAddress);
        int auxAddress = dataIndexToDataAddress(auxIndex);
        if (this.currentSlot > auxIndex) {
            int slotsToMove = this.currentSlot - auxIndex;
            boolean value$iv3 = slotsToMove < 3;
            if (!value$iv3) {
                PreconditionsKt.throwIllegalStateException("Moving more than two slot not supported");
            }
            if (slotsToMove > 1) {
                this.slots[auxAddress + 2] = this.slots[auxAddress + 1];
            }
            this.slots[auxAddress + 1] = this.slots[auxAddress];
        }
        SlotTableKt.addAux(this.groups, parentGroupAddress);
        this.slots[auxAddress] = value;
        this.currentSlot++;
    }

    public final void updateToTableMaps() {
        this.sourceInformationMap = this.table.getSourceInformationMap$runtime();
        this.calledByMap = this.table.getCalledByMap$runtime();
    }

    public final void recordGroupSourceInformation(String sourceInformation) {
        if (this.insertCount > 0) {
            groupSourceInformationFor(this.parent, sourceInformation);
        }
    }

    public final void recordGrouplessCallSourceInformationStart(int key, String value) {
        if (this.insertCount > 0) {
            MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = this.calledByMap;
            if (mutableIntObjectMap != null) {
                SlotTableKt.add(mutableIntObjectMap, key, groupKey(this.parent));
            }
            GapGroupSourceInformation gapGroupSourceInformationGroupSourceInformationFor = groupSourceInformationFor(this.parent, null);
            if (gapGroupSourceInformationGroupSourceInformationFor != null) {
                gapGroupSourceInformationGroupSourceInformationFor.startGrouplessCall(key, value, getCurrentGroupSlotIndex());
            }
        }
    }

    public final void recordGrouplessCallSourceInformationEnd() {
        GapGroupSourceInformation gapGroupSourceInformationGroupSourceInformationFor;
        if (this.insertCount <= 0 || (gapGroupSourceInformationGroupSourceInformationFor = groupSourceInformationFor(this.parent, null)) == null) {
            return;
        }
        gapGroupSourceInformationGroupSourceInformationFor.endGrouplessCall(getCurrentGroupSlotIndex());
    }

    private final GapGroupSourceInformation groupSourceInformationFor(int parent, String sourceInformation) {
        GapGroupSourceInformation result;
        Map map = this.sourceInformationMap;
        if (map == null) {
            return null;
        }
        Map $this$getOrPut$iv = map;
        GapAnchor gapAnchorAnchor = anchor(parent);
        GapGroupSourceInformation gapGroupSourceInformation = $this$getOrPut$iv.get(gapAnchorAnchor);
        if (gapGroupSourceInformation == null) {
            result = new GapGroupSourceInformation(0, sourceInformation, 0);
            if (sourceInformation == null) {
                int child = parent + 1;
                int end = this.currentGroup;
                while (child < end) {
                    result.reportGroup(this, child);
                    child += SlotTableKt.groupSize(this.groups, child);
                }
            }
            $this$getOrPut$iv.put(gapAnchorAnchor, result);
        } else {
            result = gapGroupSourceInformation;
        }
        return result;
    }

    public final void updateNode(Object value) {
        updateNodeOfGroup(this.currentGroup, value);
    }

    public final void updateNode(GapAnchor anchor, Object value) {
        updateNodeOfGroup(anchor.toIndexFor(this), value);
    }

    public final void updateParentNode(Object value) {
        updateNodeOfGroup(this.parent, value);
    }

    public final void set(Object value) {
        boolean value$iv = this.currentSlot <= this.currentSlotEnd;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Writing to an invalid slot");
        }
        this.slots[dataIndexToDataAddress(this.currentSlot - 1)] = value;
    }

    public final Object set(int index, Object value) {
        return set(getCurrentGroup(), index, value);
    }

    public final int slotIndexOfGroupSlotIndex(int group, int index) {
        int address = groupIndexToAddress(group);
        int slotsStart = slotIndex(this.groups, address);
        int slotsEnd = dataIndex(this.groups, groupIndexToAddress(group + 1));
        int slotsIndex = slotsStart + index;
        boolean value$iv = slotsIndex >= slotsStart && slotsIndex < slotsEnd;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Write to an invalid slot index " + index + " for group " + group);
        }
        return slotsIndex;
    }

    public final Object set(int group, int index, Object value) {
        int slotsIndex = slotIndexOfGroupSlotIndex(group, index);
        int slotAddress = dataIndexToDataAddress(slotsIndex);
        Object result = this.slots[slotAddress];
        this.slots[slotAddress] = value;
        return result;
    }

    public final Object clear(int slotIndex) {
        int address = dataIndexToDataAddress(slotIndex);
        Object result = this.slots[address];
        this.slots[address] = Composer.INSTANCE.getEmpty();
        return result;
    }

    public final Object skip() {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        return objArr[dataIndexToDataAddress(i)];
    }

    public final Object slot(GapAnchor anchor, int index) {
        return slot(anchorIndex(anchor), index);
    }

    public final Object slot(int groupIndex, int index) {
        int address = groupIndexToAddress(groupIndex);
        int slotsStart = slotIndex(this.groups, address);
        int slotsEnd = dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
        int slotsIndex = slotsStart + index;
        boolean z = false;
        if (slotsStart <= slotsIndex && slotsIndex < slotsEnd) {
            z = true;
        }
        if (!z) {
            return Composer.INSTANCE.getEmpty();
        }
        int slotAddress = dataIndexToDataAddress(slotsIndex);
        return this.slots[slotAddress];
    }

    public final void forEachTailSlot(int groupIndex, int count, Function2<? super Integer, Object, Unit> block) {
        int slotsStart = slotsStartIndex$runtime(groupIndex);
        int slotsEnd = slotsEndIndex$runtime(groupIndex);
        for (int slotIndex = Math.max(slotsStart, slotsEnd - count); slotIndex < slotsEnd; slotIndex++) {
            block.invoke(Integer.valueOf(slotIndex), this.slots[dataIndexToDataAddress(slotIndex)]);
        }
    }

    public final int slotsStartIndex$runtime(int groupIndex) {
        return slotIndex(this.groups, groupIndexToAddress(groupIndex));
    }

    public final int slotsEndIndex$runtime(int groupIndex) {
        return dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
    }

    public final int slotsEndAllIndex$runtime(int groupIndex) {
        return dataIndex(this.groups, groupIndexToAddress(groupSize(groupIndex) + groupIndex));
    }

    private final int getCurrentGroupSlotIndex() {
        return groupSlotIndex(this.parent);
    }

    public final int groupSlotIndex(int group) {
        MutableObjectList<Object> mutableObjectList;
        int iSlotsStartIndex$runtime = this.currentSlot - slotsStartIndex$runtime(group);
        MutableIntObjectMap<MutableObjectList<Object>> mutableIntObjectMap = this.deferredSlotWrites;
        return iSlotsStartIndex$runtime + ((mutableIntObjectMap == null || (mutableObjectList = mutableIntObjectMap.get(group)) == null) ? 0 : mutableObjectList.getSize());
    }

    public final void advanceBy(int amount) {
        boolean value$iv = amount >= 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek backwards");
        }
        boolean value$iv2 = this.insertCount <= 0;
        if (!value$iv2) {
            PreconditionsKt.throwIllegalStateException("Cannot call seek() while inserting");
        }
        if (amount == 0) {
            return;
        }
        int index = this.currentGroup + amount;
        boolean value$iv3 = index >= this.parent && index <= this.currentGroupEnd;
        if (!value$iv3) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek outside the current group (" + this.parent + '-' + this.currentGroupEnd + ')');
        }
        this.currentGroup = index;
        int newSlot = dataIndex(this.groups, groupIndexToAddress(index));
        this.currentSlot = newSlot;
        this.currentSlotEnd = newSlot;
    }

    public final void seek(GapAnchor anchor) {
        advanceBy(anchor.toIndexFor(this) - this.currentGroup);
    }

    public final void skipToGroupEnd() {
        int newGroup = this.currentGroupEnd;
        this.currentGroup = newGroup;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(newGroup));
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            saveCurrentGroupEnd();
        }
    }

    public final void endInsert() {
        boolean value$iv = this.insertCount > 0;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("Unbalanced begin/end insert");
        }
        this.insertCount--;
        if (this.insertCount == 0) {
            IntStack this_$iv = this.nodeCountStack;
            int i = this_$iv.tos;
            IntStack this_$iv2 = this.startStack;
            boolean value$iv2 = i == this_$iv2.tos;
            if (!value$iv2) {
                ComposerKt.composeImmediateRuntimeError("startGroup/endGroup mismatch while inserting");
            }
            restoreCurrentGroupEnd();
        }
    }

    public final void startGroup() {
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Key must be supplied when inserting");
        }
        startGroup(0, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key, Object dataKey) {
        startGroup(key, dataKey, false, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(int key, Object objectKey) {
        startGroup(key, objectKey, true, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(int key, Object objectKey, Object node) {
        startGroup(key, objectKey, true, node);
    }

    public final void startData(int key, Object objectKey, Object aux) {
        startGroup(key, objectKey, false, aux);
    }

    public final void startData(int key, Object aux) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, aux);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void startGroup(int key, Object objectKey, boolean isNode, Object aux) {
        int iGroupSize;
        int i;
        GapGroupSourceInformation gapGroupSourceInformationSourceInformationOf$runtime;
        int i2 = this.parent;
        byte b = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        if (b != false) {
            int i3 = this.currentGroup;
            int iDataIndex = dataIndex(this.groups, groupIndexToAddress(i3));
            insertGroups(1);
            this.currentSlot = iDataIndex;
            this.currentSlotEnd = iDataIndex;
            int iGroupIndexToAddress = groupIndexToAddress(i3);
            boolean z = objectKey != Composer.INSTANCE.getEmpty();
            boolean z2 = (isNode || aux == Composer.INSTANCE.getEmpty()) ? false : true;
            int iDataIndexToDataAnchor = dataIndexToDataAnchor(iDataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length);
            if (iDataIndexToDataAnchor >= 0 && this.slotsGapOwner < i3) {
                i = -(((this.slots.length - this.slotsGapLen) - iDataIndexToDataAnchor) + 1);
            } else {
                i = iDataIndexToDataAnchor;
            }
            SlotTableKt.initGroup(this.groups, iGroupIndexToAddress, key, isNode, z, z2, this.parent, i);
            int i4 = (isNode ? 1 : 0) + (z ? 1 : 0) + (z2 ? 1 : 0);
            if (i4 > 0) {
                insertSlots(i4, i3);
                Object[] objArr = this.slots;
                int i5 = this.currentSlot;
                if (isNode) {
                    objArr[i5] = aux;
                    i5++;
                }
                if (z) {
                    objArr[i5] = objectKey;
                    i5++;
                }
                if (z2) {
                    objArr[i5] = aux;
                    i5++;
                }
                this.currentSlot = i5;
            }
            this.nodeCount = 0;
            iGroupSize = i3 + 1;
            this.parent = i3;
            this.currentGroup = iGroupSize;
            if (i2 >= 0 && (gapGroupSourceInformationSourceInformationOf$runtime = sourceInformationOf$runtime(i2)) != null) {
                gapGroupSourceInformationSourceInformationOf$runtime.reportGroup(this, i3);
            }
        } else {
            this.startStack.push(i2);
            saveCurrentGroupEnd();
            int i6 = this.currentGroup;
            int iGroupIndexToAddress2 = groupIndexToAddress(i6);
            if (!Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty())) {
                if (isNode) {
                    updateNode(aux);
                } else {
                    updateAux(aux);
                }
            }
            this.currentSlot = slotIndex(this.groups, iGroupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            this.nodeCount = this.groups[(iGroupIndexToAddress2 * 5) + 1] & 67108863;
            this.parent = i6;
            this.currentGroup = i6 + 1;
            iGroupSize = SlotTableKt.groupSize(this.groups, iGroupIndexToAddress2) + i6;
        }
        this.currentGroupEnd = iGroupSize;
    }

    public final int endGroup() {
        int oldGroupSize;
        int oldNodes;
        int i;
        ObjectList objectList;
        boolean inserting = this.insertCount > 0;
        int currentGroup = this.currentGroup;
        int currentGroupEnd = this.currentGroupEnd;
        int groupIndex = this.parent;
        int groupAddress = groupIndexToAddress(groupIndex);
        int newNodes = this.nodeCount;
        int newGroupSize = currentGroup - groupIndex;
        int[] $this$isNode$iv = this.groups;
        int currentAddress = 1073741824;
        boolean isNode = ($this$isNode$iv[(groupAddress * 5) + 1] & 1073741824) != 0;
        if (inserting) {
            MutableIntObjectMap<MutableObjectList<Object>> mutableIntObjectMap = this.deferredSlotWrites;
            if (mutableIntObjectMap == null || (objectList = (MutableObjectList) mutableIntObjectMap.get(groupIndex)) == null) {
                i = 1;
            } else {
                ObjectList this_$iv = objectList;
                Object[] content$iv = this_$iv.content;
                i = 1;
                int i2 = this_$iv._size;
                int i$iv = 0;
                while (i$iv < i2) {
                    int i$iv2 = i$iv;
                    Object value = content$iv[i$iv2];
                    rawUpdate(value);
                    i$iv = i$iv2 + 1;
                }
                mutableIntObjectMap.remove(groupIndex);
            }
            SlotTableKt.updateGroupSize(this.groups, groupAddress, newGroupSize);
            SlotTableKt.updateNodeCount(this.groups, groupAddress, newNodes);
            this.nodeCount = this.nodeCountStack.pop() + (isNode ? i : newNodes);
            this.parent = parent(this.groups, groupIndex);
            int nextAddress = this.parent < 0 ? getSize$runtime() : groupIndexToAddress(this.parent + 1);
            int newCurrentSlot = nextAddress < 0 ? 0 : dataIndex(this.groups, nextAddress);
            this.currentSlot = newCurrentSlot;
            this.currentSlotEnd = newCurrentSlot;
        } else {
            boolean value$iv = currentGroup == currentGroupEnd;
            if (!value$iv) {
                ComposerKt.composeImmediateRuntimeError("Expected to be at the end of a group");
            }
            int oldGroupSize2 = SlotTableKt.groupSize(this.groups, groupAddress);
            int[] $this$nodeCount$iv = this.groups;
            int oldNodes2 = $this$nodeCount$iv[(groupAddress * 5) + 1] & 67108863;
            SlotTableKt.updateGroupSize(this.groups, groupAddress, newGroupSize);
            SlotTableKt.updateNodeCount(this.groups, groupAddress, newNodes);
            int newParent = this.startStack.pop();
            restoreCurrentGroupEnd();
            this.parent = newParent;
            int groupParent = parent(this.groups, groupIndex);
            this.nodeCount = this.nodeCountStack.pop();
            if (groupParent == newParent) {
                this.nodeCount += isNode ? 0 : newNodes - oldNodes2;
            } else {
                int groupSizeDelta = newGroupSize - oldGroupSize2;
                int nodesDelta = isNode ? 0 : newNodes - oldNodes2;
                if (groupSizeDelta != 0 || nodesDelta != 0) {
                    int current = groupParent;
                    while (current != 0 && current != newParent && (nodesDelta != 0 || groupSizeDelta != 0)) {
                        int i3 = currentAddress;
                        int currentAddress2 = groupIndexToAddress(current);
                        if (groupSizeDelta == 0) {
                            oldGroupSize = oldGroupSize2;
                            oldNodes = oldNodes2;
                        } else {
                            oldGroupSize = oldGroupSize2;
                            int newSize = SlotTableKt.groupSize(this.groups, currentAddress2) + groupSizeDelta;
                            oldNodes = oldNodes2;
                            SlotTableKt.updateGroupSize(this.groups, currentAddress2, newSize);
                        }
                        if (nodesDelta != 0) {
                            int[] iArr = this.groups;
                            int[] $this$nodeCount$iv2 = this.groups;
                            SlotTableKt.updateNodeCount(iArr, currentAddress2, ($this$nodeCount$iv2[(currentAddress2 * 5) + 1] & 67108863) + nodesDelta);
                        }
                        int[] $this$isNode$iv2 = this.groups;
                        if (($this$isNode$iv2[(currentAddress2 * 5) + 1] & i3) != 0) {
                            nodesDelta = 0;
                        }
                        current = parent(this.groups, current);
                        currentAddress = i3;
                        oldGroupSize2 = oldGroupSize;
                        oldNodes2 = oldNodes;
                    }
                }
                this.nodeCount += nodesDelta;
            }
        }
        return newNodes;
    }

    public final void ensureStarted(int index) {
        boolean value$iv = this.insertCount <= 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot call ensureStarted() while inserting");
        }
        int parent = this.parent;
        if (parent != index) {
            boolean value$iv2 = index >= parent && index < this.currentGroupEnd;
            if (!value$iv2) {
                ComposerKt.composeImmediateRuntimeError("Started group at " + index + " must be a subgroup of the group at " + parent);
            }
            int oldCurrent = this.currentGroup;
            int oldCurrentSlot = this.currentSlot;
            int oldCurrentSlotEnd = this.currentSlotEnd;
            this.currentGroup = index;
            startGroup();
            this.currentGroup = oldCurrent;
            this.currentSlot = oldCurrentSlot;
            this.currentSlotEnd = oldCurrentSlotEnd;
        }
    }

    public final void ensureStarted(GapAnchor anchor) {
        ensureStarted(anchor.toIndexFor(this));
    }

    public final int skipGroup() {
        int groupAddress = groupIndexToAddress(this.currentGroup);
        int newGroup = this.currentGroup + SlotTableKt.groupSize(this.groups, groupAddress);
        this.currentGroup = newGroup;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(newGroup));
        int[] $this$isNode$iv = this.groups;
        if (($this$isNode$iv[(groupAddress * 5) + 1] & 1073741824) != 0) {
            return 1;
        }
        int[] $this$nodeCount$iv = this.groups;
        return 67108863 & $this$nodeCount$iv[(groupAddress * 5) + 1];
    }

    public final boolean removeGroup() {
        GapAnchor anchor;
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot remove group while inserting");
        }
        int oldGroup = this.currentGroup;
        int oldSlot = this.currentSlot;
        int dataStart = dataIndex(this.groups, groupIndexToAddress(oldGroup));
        int count = skipGroup();
        GapGroupSourceInformation sourceInformation = sourceInformationOf$runtime(this.parent);
        if (sourceInformation != null && (anchor = tryAnchor$runtime(oldGroup)) != null) {
            sourceInformation.removeAnchor(anchor);
        }
        MutableIntList it = this.pendingRecalculateMarks;
        if (it != null) {
            while (PrioritySet.m4513isNotEmptyimpl(it) && PrioritySet.m4514peekimpl(it) >= oldGroup) {
                PrioritySet.m4515takeMaximpl(it);
            }
        }
        boolean anchorsRemoved = removeGroups(oldGroup, this.currentGroup - oldGroup);
        removeSlots(dataStart, this.currentSlot - dataStart, oldGroup - 1);
        this.currentGroup = oldGroup;
        this.currentSlot = oldSlot;
        this.nodeCount -= count;
        return anchorsRemoved;
    }

    public final Iterator<Object> groupSlots() {
        int start = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int end = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + groupSize(this.currentGroup)));
        return new AnonymousClass1(start, end, this);
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.composer.gapbuffer.SlotWriter$groupSlots$1, reason: invalid class name */
    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001J\t\u0010\t\u001a\u00020\nH\u0096\u0002J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0002H\u0096\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\f"}, d2 = {"androidx/compose/runtime/composer/gapbuffer/SlotWriter$groupSlots$1", "", "", "current", "", "getCurrent", "()I", "setCurrent", "(I)V", "hasNext", "", "next", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class AnonymousClass1 implements Iterator<Object>, KMappedMarker {
        final /* synthetic */ int $end;
        private int current;
        final /* synthetic */ SlotWriter this$0;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        AnonymousClass1(int $start, int $end, SlotWriter $receiver) {
            this.$end = $end;
            this.this$0 = $receiver;
            this.current = $start;
        }

        public final int getCurrent() {
            return this.current;
        }

        public final void setCurrent(int i) {
            this.current = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.current < this.$end;
        }

        @Override // java.util.Iterator
        public Object next() {
            if (!hasNext()) {
                return null;
            }
            Object[] objArr = this.this$0.slots;
            SlotWriter slotWriter = this.this$0;
            int i = this.current;
            this.current = i + 1;
            return objArr[slotWriter.dataIndexToDataAddress(i)];
        }
    }

    public final void forAllData(int group, Function2<? super Integer, Object, Unit> block) {
        int address = groupIndexToAddress(group);
        int start = dataIndex(this.groups, address);
        int end = dataIndex(this.groups, groupIndexToAddress(getCurrentGroup() + groupSize(getCurrentGroup())));
        for (int slot = start; slot < end; slot++) {
            block.invoke(Integer.valueOf(slot), this.slots[dataIndexToDataAddress(slot)]);
        }
    }

    public final void traverseGroupAndChildren(int group, Function1<? super Integer, Unit> enter, Function1<? super Integer, Unit> exit) {
        int current = group;
        int currentParent = parent(current);
        int size = getSize$runtime();
        int end = groupSize(group) + group;
        while (current < end) {
            enter.invoke(Integer.valueOf(current));
            int next = current + 1;
            int nextParent = next < size ? parent(next) : -1;
            if (nextParent != current) {
                while (true) {
                    exit.invoke(Integer.valueOf(current));
                    if (current == group || currentParent == nextParent) {
                        break;
                    }
                    current = currentParent;
                    currentParent = parent(current);
                }
            }
            current = next;
            currentParent = nextParent;
        }
    }

    public final void forAllDataInRememberOrder(int group, Function2<? super Integer, Object, Unit> block) {
        int next$iv;
        int size$iv;
        int child;
        int i;
        int $i$f$traverseGroupAndChildren;
        int currentParent$iv;
        int end$iv;
        int after;
        MutableIntSet mutableIntSet;
        MutableIntList it;
        SlotWriter slotWriter = this;
        MutableIntList mutableIntList = null;
        MutableIntSet mutableIntSet2 = null;
        int $i$f$traverseGroupAndChildren2 = 0;
        int current$iv = group;
        int currentParent$iv2 = parent(current$iv);
        int size$iv2 = getSize$runtime();
        int end$iv2 = groupSize(group) + group;
        while (current$iv < end$iv2) {
            int child2 = current$iv;
            int slotIndex = slotWriter.dataIndex(child2);
            int iDataIndex = slotWriter.dataIndex(child2 + 1);
            while (slotIndex < iDataIndex) {
                int address = slotWriter.dataIndexToDataAddress(slotIndex);
                Object value = slotWriter.slots[address];
                MutableIntList mutableIntList2 = mutableIntList;
                if ((value instanceof RememberObserverHolder) && (after = GapComposerKt.asGapRememberObserverHolder((RememberObserverHolder) value).getAfterGroupIndex()) >= 0) {
                    MutableIntSet mutableIntSet3 = mutableIntSet2;
                    int index = slotWriter.childGroupAtIndex(child2, after);
                    if (mutableIntSet3 == null) {
                        MutableIntSet mutableIntSetMutableIntSetOf = IntSetKt.mutableIntSetOf();
                        mutableIntSet = mutableIntSetMutableIntSetOf;
                        mutableIntSet3 = mutableIntSetMutableIntSetOf;
                    } else {
                        mutableIntSet = mutableIntSet3;
                    }
                    MutableIntSet afters = mutableIntSet3;
                    if (mutableIntList2 == null) {
                        $i$f$traverseGroupAndChildren = $i$f$traverseGroupAndChildren2;
                        currentParent$iv = currentParent$iv2;
                        int currentParent$iv3 = 1;
                        end$iv = end$iv2;
                        int end$iv3 = 0;
                        it = new MutableIntList(end$iv3, currentParent$iv3, null);
                        mutableIntList2 = it;
                    } else {
                        $i$f$traverseGroupAndChildren = $i$f$traverseGroupAndChildren2;
                        currentParent$iv = currentParent$iv2;
                        end$iv = end$iv2;
                        it = mutableIntList2;
                    }
                    afters.add(index);
                    it.add(index);
                    it.add(slotIndex);
                    mutableIntSet2 = mutableIntSet;
                    mutableIntList = mutableIntList2;
                    slotIndex++;
                    $i$f$traverseGroupAndChildren2 = $i$f$traverseGroupAndChildren;
                    currentParent$iv2 = currentParent$iv;
                    end$iv2 = end$iv;
                } else {
                    MutableIntSet mutableIntSet4 = mutableIntSet2;
                    $i$f$traverseGroupAndChildren = $i$f$traverseGroupAndChildren2;
                    currentParent$iv = currentParent$iv2;
                    end$iv = end$iv2;
                    block.invoke(Integer.valueOf(slotIndex), value);
                    mutableIntSet2 = mutableIntSet4;
                    mutableIntList = mutableIntList2;
                    slotIndex++;
                    $i$f$traverseGroupAndChildren2 = $i$f$traverseGroupAndChildren;
                    currentParent$iv2 = currentParent$iv;
                    end$iv2 = end$iv;
                }
            }
            MutableIntList this_$iv = mutableIntList;
            MutableIntSet mutableIntSet5 = mutableIntSet2;
            int $i$f$traverseGroupAndChildren3 = $i$f$traverseGroupAndChildren2;
            int currentParent$iv4 = currentParent$iv2;
            int end$iv4 = end$iv2;
            int next$iv2 = current$iv + 1;
            int nextParent$iv = next$iv2 < size$iv2 ? parent(next$iv2) : -1;
            if (nextParent$iv != current$iv) {
                int currentParent$iv5 = currentParent$iv4;
                while (true) {
                    int child3 = current$iv;
                    if (this_$iv == null || mutableIntSet5 == null || !mutableIntSet5.remove(child3)) {
                        next$iv = next$iv2;
                        size$iv = size$iv2;
                    } else {
                        int expected = 0;
                        int size = this_$iv._size;
                        int i2 = size / 2;
                        next$iv = next$iv2;
                        int start = 0;
                        while (start < i2) {
                            int it2 = start;
                            int i3 = start;
                            int start2 = it2 * 2;
                            int size$iv3 = size$iv2;
                            int after2 = this_$iv.get(start2);
                            if (after2 == child3) {
                                child = child3;
                                int slotIndex2 = this_$iv.get(start2 + 1);
                                i = i2;
                                Object data = slotWriter.slots[slotWriter.dataIndexToDataAddress(slotIndex2)];
                                block.invoke(Integer.valueOf(slotIndex2), data);
                            } else {
                                child = child3;
                                i = i2;
                                if (start2 != expected) {
                                    int expected2 = expected + 1;
                                    this_$iv.set(expected, after2);
                                    this_$iv.set(expected2, this_$iv.get(start2 + 1));
                                    expected = expected2 + 1;
                                } else {
                                    expected += 2;
                                }
                            }
                            start = i3 + 1;
                            slotWriter = this;
                            child3 = child;
                            size$iv2 = size$iv3;
                            i2 = i;
                        }
                        size$iv = size$iv2;
                        if (expected != size) {
                            this_$iv.removeRange(expected, size);
                        }
                    }
                    if (current$iv == group || currentParent$iv5 == nextParent$iv) {
                        break;
                    }
                    current$iv = currentParent$iv5;
                    currentParent$iv5 = parent(current$iv);
                    slotWriter = this;
                    next$iv2 = next$iv;
                    size$iv2 = size$iv;
                }
            } else {
                next$iv = next$iv2;
                size$iv = size$iv2;
            }
            current$iv = next$iv;
            currentParent$iv2 = nextParent$iv;
            slotWriter = this;
            mutableIntList = this_$iv;
            mutableIntSet2 = mutableIntSet5;
            $i$f$traverseGroupAndChildren2 = $i$f$traverseGroupAndChildren3;
            end$iv2 = end$iv4;
            size$iv2 = size$iv;
        }
    }

    private final int childGroupAtIndex(int parent, int index) {
        int end = groupSize(parent) + parent;
        int childGroup = parent + 1;
        int current = 0;
        while (childGroup < end && current < index) {
            int childAddress = groupIndexToAddress(childGroup);
            childGroup += SlotTableKt.groupSize(this.groups, childAddress);
            if (childGroup < end) {
                int[] $this$hasObjectKey$iv = this.groups;
                if (!(($this$hasObjectKey$iv[(childAddress * 5) + 1] & GroupFlagsKt.HasMovableContentFlag) != 0)) {
                    current++;
                }
            }
        }
        return childGroup;
    }

    public final void moveGroup(int offset) {
        int[] iArr;
        int destinationOffset$iv;
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot move a group while inserting");
        }
        boolean value$iv2 = offset >= 0;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
        }
        if (offset == 0) {
            return;
        }
        int current = this.currentGroup;
        int parent = this.parent;
        int parentEnd = this.currentGroupEnd;
        int count = offset;
        int groupToMove = current;
        while (true) {
            iArr = this.groups;
            if (count <= 0) {
                break;
            }
            groupToMove += SlotTableKt.groupSize(iArr, groupIndexToAddress(groupToMove));
            boolean value$iv3 = groupToMove <= parentEnd;
            if (!value$iv3) {
                ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
            }
            count--;
        }
        int moveLen = SlotTableKt.groupSize(iArr, groupIndexToAddress(groupToMove));
        int destinationSlot = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int dataStart = dataIndex(this.groups, groupIndexToAddress(groupToMove));
        int dataEnd = dataIndex(this.groups, groupIndexToAddress(groupToMove + moveLen));
        int moveDataLen = dataEnd - dataStart;
        insertSlots(moveDataLen, Math.max(this.currentGroup - 1, 0));
        insertGroups(moveLen);
        int[] groups = this.groups;
        int moveLocationAddress = groupIndexToAddress(groupToMove + moveLen);
        int moveLocationOffset = moveLocationAddress * 5;
        int currentAddress = groupIndexToAddress(current);
        ArraysKt.copyInto(groups, groups, currentAddress * 5, moveLocationOffset, moveLocationOffset + (moveLen * 5));
        if (moveDataLen > 0) {
            Object[] slots = this.slots;
            int startIndex$iv = dataIndexToDataAddress(dataStart + moveDataLen);
            int endIndex$iv = dataIndexToDataAddress(dataEnd + moveDataLen);
            destinationOffset$iv = groupToMove;
            System.arraycopy(slots, startIndex$iv, slots, destinationSlot, endIndex$iv - startIndex$iv);
        } else {
            destinationOffset$iv = groupToMove;
        }
        int dataMoveDistance = (dataStart + moveDataLen) - destinationSlot;
        int slotsGapStart = this.slotsGapStart;
        int slotsGapLen = this.slotsGapLen;
        int slotsCapacity = this.slots.length;
        int slotsGapOwner = this.slotsGapOwner;
        int dataMoveDistance2 = current + moveLen;
        int newIndex = current;
        while (newIndex < dataMoveDistance2) {
            int i = dataMoveDistance2;
            int groupAddress = groupIndexToAddress(newIndex);
            int oldIndex = dataIndex(groups, groupAddress);
            int group = newIndex;
            int group2 = oldIndex - dataMoveDistance;
            int slotsGapOwner2 = slotsGapOwner;
            int slotsGapOwner3 = slotsGapOwner < groupAddress ? 0 : slotsGapStart;
            int newAnchor = dataIndexToDataAnchor(group2, slotsGapOwner3, slotsGapLen, slotsCapacity);
            updateDataIndex(groups, groupAddress, newAnchor);
            newIndex = group + 1;
            dataMoveDistance2 = i;
            slotsGapOwner = slotsGapOwner2;
        }
        int slotsGapOwner4 = destinationOffset$iv + moveLen;
        moveAnchors(slotsGapOwner4, current, moveLen);
        boolean anchorsRemoved = removeGroups(destinationOffset$iv + moveLen, moveLen);
        boolean value$iv4 = !anchorsRemoved;
        if (!value$iv4) {
            ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
        }
        fixParentAnchorsFor(parent, this.currentGroupEnd, current);
        if (moveDataLen > 0) {
            removeSlots(dataStart + moveDataLen, moveDataLen, (destinationOffset$iv + moveLen) - 1);
        }
    }

    public final boolean inGroup(GapAnchor groupAnchor, GapAnchor anchor) {
        int group = anchorIndex(groupAnchor);
        int groupEnd = SlotTableKt.groupSize(this.groups, group) + group;
        int location$runtime = anchor.getLocation();
        return group <= location$runtime && location$runtime < groupEnd;
    }

    /* JADX INFO: compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J@\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rH\u0002¨\u0006\u0010"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/SlotWriter$Companion;", "", "<init>", "()V", "moveGroup", "", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "fromWriter", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "fromIndex", "", "toWriter", "updateFromCursor", "", "updateToCursor", "removeSourceGroup", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static /* synthetic */ List moveGroup$default(Companion companion, SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2, boolean z3, int i2, Object obj) {
            boolean z4;
            if ((i2 & 32) == 0) {
                z4 = z3;
            } else {
                z4 = true;
            }
            return companion.moveGroup(slotWriter, i, slotWriter2, z, z2, z4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<GapAnchor> moveGroup(SlotWriter fromWriter, int fromIndex, SlotWriter toWriter, boolean updateFromCursor, boolean updateToCursor, boolean removeSourceGroup) {
            ArrayList arrayListEmptyList;
            List<GapAnchor> list;
            boolean anchorsRemoved;
            HashMap sourceSourceInformationMap;
            int groupsToMove = fromWriter.groupSize(fromIndex);
            int sourceGroupsEnd = fromIndex + groupsToMove;
            int sourceSlotsStart = fromWriter.dataIndex(fromIndex);
            int sourceSlotsEnd = fromWriter.dataIndex(sourceGroupsEnd);
            int slotsToMove = sourceSlotsEnd - sourceSlotsStart;
            boolean hasMarks = fromWriter.containsAnyGroupMarks(fromIndex);
            toWriter.insertGroups(groupsToMove);
            toWriter.insertSlots(slotsToMove, toWriter.getCurrentGroup());
            if (fromWriter.groupGapStart < sourceGroupsEnd) {
                fromWriter.moveGroupGapTo(sourceGroupsEnd);
            }
            if (fromWriter.slotsGapStart < sourceSlotsEnd) {
                fromWriter.moveSlotGapTo(sourceSlotsEnd, sourceGroupsEnd);
            }
            int[] groups = toWriter.groups;
            int currentGroup = toWriter.getCurrentGroup();
            ArraysKt.copyInto(fromWriter.groups, groups, currentGroup * 5, fromIndex * 5, sourceGroupsEnd * 5);
            Object[] slots = toWriter.slots;
            int currentSlot = toWriter.currentSlot;
            Object[] $this$fastCopyInto$iv = fromWriter.slots;
            System.arraycopy($this$fastCopyInto$iv, sourceSlotsStart, slots, currentSlot, sourceSlotsEnd - sourceSlotsStart);
            int parent = toWriter.getParent();
            groups[(currentGroup * 5) + 2] = parent;
            int value$iv = currentGroup - fromIndex;
            int moveEnd = currentGroup + groupsToMove;
            int dataIndexDelta = currentSlot - toWriter.dataIndex(groups, currentGroup);
            int slotsGapOwner = toWriter.slotsGapOwner;
            int slotsGapLen = toWriter.slotsGapLen;
            int currentSlot2 = slots.length;
            int groupAddress = currentGroup;
            while (true) {
                if (groupAddress >= moveEnd) {
                    break;
                }
                if (groupAddress != currentGroup) {
                    int address$iv = groupAddress;
                    int[] $this$parentAnchor$iv = groups;
                    int[] $this$updateParentAnchor$iv = groups;
                    int address$iv2 = groupAddress;
                    $this$updateParentAnchor$iv[(address$iv2 * 5) + 2] = $this$parentAnchor$iv[(address$iv * 5) + 2] + value$iv;
                }
                int currentGroup2 = currentGroup;
                int newDataIndex = toWriter.dataIndex(groups, groupAddress) + dataIndexDelta;
                int[] groups2 = groups;
                int moveEnd2 = moveEnd;
                int newDataAnchor = toWriter.dataIndexToDataAnchor(newDataIndex, slotsGapOwner >= groupAddress ? toWriter.slotsGapStart : 0, slotsGapLen, currentSlot2);
                int address$iv3 = groupAddress;
                groups2[(address$iv3 * 5) + 4] = newDataAnchor;
                if (groupAddress == slotsGapOwner) {
                    slotsGapOwner++;
                }
                groupAddress++;
                currentGroup = currentGroup2;
                groups = groups2;
                moveEnd = moveEnd2;
            }
            int[] groups3 = groups;
            int currentGroup3 = currentGroup;
            toWriter.slotsGapOwner = slotsGapOwner;
            int startAnchors = SlotTableKt.locationOf(fromWriter.anchors, fromIndex, fromWriter.getSize$runtime());
            int endAnchors = SlotTableKt.locationOf(fromWriter.anchors, sourceGroupsEnd, fromWriter.getSize$runtime());
            if (startAnchors < endAnchors) {
                ArrayList sourceAnchors = fromWriter.anchors;
                ArrayList anchors = new ArrayList(endAnchors - startAnchors);
                int anchorDelta = currentGroup3 - fromIndex;
                int anchorDelta2 = startAnchors;
                while (anchorDelta2 < endAnchors) {
                    int anchorIndex = anchorDelta2;
                    GapAnchor sourceAnchor = (GapAnchor) sourceAnchors.get(anchorDelta2);
                    sourceAnchor.setLocation$runtime(sourceAnchor.getLocation() + anchorDelta);
                    anchors.add(sourceAnchor);
                    anchorDelta2 = anchorIndex + 1;
                    slotsGapLen = slotsGapLen;
                }
                ArrayList arrayList = toWriter.anchors;
                int currentGroup4 = toWriter.getCurrentGroup();
                int slotsCapacity = toWriter.getSize$runtime();
                int insertLocation = SlotTableKt.locationOf(arrayList, currentGroup4, slotsCapacity);
                toWriter.anchors.addAll(insertLocation, anchors);
                sourceAnchors.subList(startAnchors, endAnchors).clear();
                arrayListEmptyList = anchors;
            } else {
                arrayListEmptyList = CollectionsKt.emptyList();
            }
            if (!arrayListEmptyList.isEmpty()) {
                HashMap sourceSourceInformationMap2 = fromWriter.sourceInformationMap;
                HashMap destinationSourceInformation = toWriter.sourceInformationMap;
                if (sourceSourceInformationMap2 == null || destinationSourceInformation == null) {
                    list = arrayListEmptyList;
                } else {
                    List<GapAnchor> list2 = arrayListEmptyList;
                    list = arrayListEmptyList;
                    int size = list2.size();
                    int startAnchors2 = 0;
                    while (startAnchors2 < size) {
                        Object item$iv = list2.get(startAnchors2);
                        int i = size;
                        GapAnchor anchor = (GapAnchor) item$iv;
                        int index$iv = startAnchors2;
                        GapGroupSourceInformation information = (GapGroupSourceInformation) sourceSourceInformationMap2.get(anchor);
                        if (information == null) {
                            sourceSourceInformationMap = sourceSourceInformationMap2;
                        } else {
                            sourceSourceInformationMap2.remove(anchor);
                            sourceSourceInformationMap = sourceSourceInformationMap2;
                            destinationSourceInformation.put(anchor, information);
                        }
                        startAnchors2 = index$iv + 1;
                        size = i;
                        sourceSourceInformationMap2 = sourceSourceInformationMap;
                    }
                }
            } else {
                list = arrayListEmptyList;
            }
            int toWriterParent = toWriter.getParent();
            GapGroupSourceInformation it = toWriter.sourceInformationOf$runtime(parent);
            if (it != null) {
                int predecessor = -1;
                int child = toWriterParent + 1;
                int endGroup = toWriter.getCurrentGroup();
                while (child < endGroup) {
                    predecessor = child;
                    child += SlotTableKt.groupSize(toWriter.groups, child);
                    toWriterParent = toWriterParent;
                }
                it.addGroupAfter(toWriter, predecessor, endGroup);
            }
            int parentGroup = fromWriter.parent(fromIndex);
            int i2 = 1;
            if (!removeSourceGroup) {
                anchorsRemoved = false;
            } else if (!updateFromCursor) {
                anchorsRemoved = fromWriter.removeGroups(fromIndex, groupsToMove);
                fromWriter.removeSlots(sourceSlotsStart, slotsToMove, fromIndex - 1);
            } else {
                boolean needsStartGroups = parentGroup >= 0;
                if (needsStartGroups) {
                    fromWriter.startGroup();
                    fromWriter.advanceBy(parentGroup - fromWriter.getCurrentGroup());
                    fromWriter.startGroup();
                }
                fromWriter.advanceBy(fromIndex - fromWriter.getCurrentGroup());
                anchorsRemoved = fromWriter.removeGroup();
                if (needsStartGroups) {
                    fromWriter.skipToGroupEnd();
                    fromWriter.endGroup();
                    fromWriter.skipToGroupEnd();
                    fromWriter.endGroup();
                }
            }
            boolean value$iv2 = !anchorsRemoved;
            if (!value$iv2) {
                ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
            }
            int i3 = toWriter.nodeCount;
            if (((groups3[(currentGroup3 * 5) + 1] & 1073741824) != 0 ? 1 : 0) == 0) {
                i2 = groups3[(currentGroup3 * 5) + 1] & 67108863;
            }
            toWriter.nodeCount = i3 + i2;
            if (updateToCursor) {
                toWriter.currentGroup = currentGroup3 + groupsToMove;
                toWriter.currentSlot = currentSlot + slotsToMove;
            }
            if (hasMarks) {
                toWriter.updateContainsMark(parent);
            }
            return list;
        }
    }

    public final List<GapAnchor> moveTo(GapAnchor anchor, int offset, SlotWriter writer) {
        boolean value$iv = writer.insertCount > 0;
        boolean value$iv$iv = value$iv;
        if (!value$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        boolean value$iv2 = this.insertCount == 0;
        boolean value$iv$iv2 = value$iv2;
        if (!value$iv$iv2) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        boolean value$iv3 = anchor.getValid();
        if (!value$iv3) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        int location = anchorIndex(anchor) + offset;
        int currentGroup = this.currentGroup;
        boolean value$iv4 = currentGroup <= location && location < this.currentGroupEnd;
        boolean value$iv$iv3 = value$iv4;
        if (!value$iv$iv3) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        int parent = parent(location);
        int size = groupSize(location);
        int nodes = isNode(location) ? 1 : nodeCount(location);
        List<GapAnchor> listMoveGroup$default = Companion.moveGroup$default(INSTANCE, this, location, writer, false, false, false, 32, null);
        updateContainsMark(parent);
        int current = parent;
        boolean updatingNodes = nodes > 0;
        while (current >= currentGroup) {
            int currentAddress = groupIndexToAddress(current);
            SlotTableKt.updateGroupSize(this.groups, currentAddress, SlotTableKt.groupSize(this.groups, currentAddress) - size);
            if (updatingNodes) {
                int[] $this$isNode$iv = this.groups;
                if (($this$isNode$iv[(currentAddress * 5) + 1] & 1073741824) != 0) {
                    updatingNodes = false;
                } else {
                    int[] iArr = this.groups;
                    int[] $this$nodeCount$iv = this.groups;
                    SlotTableKt.updateNodeCount(iArr, currentAddress, ($this$nodeCount$iv[(currentAddress * 5) + 1] & 67108863) - nodes);
                }
            }
            current = parent(current);
        }
        if (updatingNodes) {
            boolean value$iv5 = this.nodeCount >= nodes;
            boolean value$iv$iv4 = value$iv5;
            if (!value$iv$iv4) {
                ComposerKt.composeImmediateRuntimeError("Check failed");
            }
            int $i$f$runtimeCheck = this.nodeCount;
            this.nodeCount = $i$f$runtimeCheck - nodes;
        }
        return listMoveGroup$default;
    }

    public static /* synthetic */ List moveFrom$default(SlotWriter slotWriter, SlotTable slotTable, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return slotWriter.moveFrom(slotTable, i, z);
    }

    public final List<GapAnchor> moveFrom(SlotTable table, int index, boolean removeSourceGroup) {
        boolean value$iv = this.insertCount > 0;
        boolean value$iv$iv = value$iv;
        if (!value$iv$iv) {
            ComposerKt.composeImmediateRuntimeError("Check failed");
        }
        if (index == 0 && this.currentGroup == 0 && this.table.getGroupsSize() == 0 && SlotTableKt.groupSize(table.getGroups(), index) == table.getGroupsSize()) {
            int[] myGroups = this.groups;
            Object[] mySlots = this.slots;
            ArrayList<GapAnchor> arrayList = this.anchors;
            HashMap<GapAnchor, GapGroupSourceInformation> map = this.sourceInformationMap;
            MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = this.calledByMap;
            int[] groups = table.getGroups();
            int groupsSize = table.getGroupsSize();
            Object[] slots = table.getSlots();
            int slotsSize = table.getSlotsSize();
            HashMap<GapAnchor, GapGroupSourceInformation> sourceInformationMap$runtime = table.getSourceInformationMap$runtime();
            MutableIntObjectMap<MutableIntSet> calledByMap$runtime = table.getCalledByMap$runtime();
            this.groups = groups;
            this.slots = slots;
            this.anchors = table.getAnchors$runtime();
            this.groupGapStart = groupsSize;
            this.groupGapLen = (groups.length / 5) - groupsSize;
            this.slotsGapStart = slotsSize;
            this.slotsGapLen = slots.length - slotsSize;
            this.slotsGapOwner = groupsSize;
            this.sourceInformationMap = sourceInformationMap$runtime;
            this.calledByMap = calledByMap$runtime;
            table.setTo$runtime(myGroups, 0, mySlots, 0, arrayList, map, mutableIntObjectMap);
            return this.anchors;
        }
        SlotWriter writer$iv = table.openWriter();
        boolean normalClose$iv = false;
        try {
            normalClose$iv = true;
            return INSTANCE.moveGroup(writer$iv, index, this, true, true, removeSourceGroup);
        } finally {
            writer$iv.close(normalClose$iv);
        }
    }

    public final void bashCurrentGroup() {
        SlotTableKt.updateGroupKey(this.groups, this.currentGroup, -3);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<androidx.compose.runtime.composer.gapbuffer.GapAnchor> moveIntoGroupFrom(int r19, androidx.compose.runtime.composer.gapbuffer.SlotTable r20, int r21) throws java.lang.Throwable {
        /*
            r18 = this;
            r3 = r18
            int r0 = r3.insertCount
            if (r0 > 0) goto L12
            int r0 = r3.currentGroup
            int r0 = r0 + r19
            int r0 = r3.groupSize(r0)
            r1 = 1
            if (r0 != r1) goto L12
            goto L13
        L12:
            r1 = 0
        L13:
            r0 = 0
            r2 = r1
            r4 = 0
            if (r2 != 0) goto L1f
            r5 = 0
            java.lang.String r5 = "Check failed"
            androidx.compose.runtime.ComposerKt.composeImmediateRuntimeError(r5)
        L1f:
            int r9 = r3.currentGroup
            int r10 = r3.currentSlot
            int r11 = r3.currentSlotEnd
            r18.advanceBy(r19)
            r3.startGroup()
            r3.beginInsert()
            r12 = r20
            r13 = 0
            androidx.compose.runtime.composer.gapbuffer.SlotWriter r14 = r12.openWriter()
            r15 = 0
            r1 = 0
            r2 = r1
            r1 = r14
            r16 = 0
            androidx.compose.runtime.composer.gapbuffer.SlotWriter$Companion r0 = androidx.compose.runtime.composer.gapbuffer.SlotWriter.INSTANCE     // Catch: java.lang.Throwable -> L69
            r7 = 32
            r8 = 0
            r4 = 0
            r5 = 1
            r6 = 0
            r17 = r12
            r12 = r2
            r2 = r21
            java.util.List r0 = androidx.compose.runtime.composer.gapbuffer.SlotWriter.Companion.moveGroup$default(r0, r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L67
            r1 = r0
            r2 = 0
            r1 = 1
            r14.close(r1)
            r3.endInsert()
            r3.endGroup()
            r3.currentGroup = r9
            r3.currentSlot = r10
            r3.currentSlotEnd = r11
            return r0
        L67:
            r0 = move-exception
            goto L6d
        L69:
            r0 = move-exception
            r17 = r12
            r12 = r2
        L6d:
            r14.close(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.composer.gapbuffer.SlotWriter.moveIntoGroupFrom(int, androidx.compose.runtime.composer.gapbuffer.SlotTable, int):java.util.List");
    }

    public static /* synthetic */ GapAnchor anchor$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.currentGroup;
        }
        return slotWriter.anchor(i);
    }

    public final GapAnchor anchor(int index) {
        ArrayList<GapAnchor> arrayList = this.anchors;
        int effectiveSize$iv = getSize$runtime();
        int location$iv = SlotTableKt.search(arrayList, index, effectiveSize$iv);
        if (location$iv < 0) {
            GapAnchor anchor$iv = new GapAnchor(index <= this.groupGapStart ? index : -(getSize$runtime() - index));
            arrayList.add(-(location$iv + 1), anchor$iv);
            return anchor$iv;
        }
        return arrayList.get(location$iv);
    }

    public static /* synthetic */ void markGroup$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.parent;
        }
        slotWriter.markGroup(i);
    }

    public final void markGroup(int group) {
        int groupAddress = groupIndexToAddress(group);
        int[] $this$hasMark$iv = this.groups;
        if (!(($this$hasMark$iv[(groupAddress * 5) + 1] & GroupFlagsKt.HasRecompositionRequiredFlag) != 0)) {
            SlotTableKt.updateMark(this.groups, groupAddress, true);
            int[] $this$containsMark$iv = this.groups;
            if (!(($this$containsMark$iv[(groupAddress * 5) + 1] & 67108864) != 0)) {
                updateContainsMark(parent(group));
            }
        }
    }

    private final boolean containsGroupMark(int group) {
        if (group < 0) {
            return false;
        }
        int[] $this$containsMark$iv = this.groups;
        int address$iv = groupIndexToAddress(group);
        return ($this$containsMark$iv[(address$iv * 5) + 1] & 67108864) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean containsAnyGroupMarks(int group) {
        if (group < 0) {
            return false;
        }
        int[] $this$containsAnyMark$iv = this.groups;
        int address$iv = groupIndexToAddress(group);
        return ($this$containsAnyMark$iv[(address$iv * 5) + 1] & 201326592) != 0;
    }

    private final void recalculateMarks() {
        MutableIntList set = this.pendingRecalculateMarks;
        if (set != null) {
            while (PrioritySet.m4513isNotEmptyimpl(set)) {
                m4520updateContainsMarkNowqrM0pCk(PrioritySet.m4515takeMaximpl(set), set);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateContainsMark(int group) {
        if (group >= 0) {
            MutableIntList it = this.pendingRecalculateMarks;
            if (it == null) {
                it = PrioritySet.m4508constructorimpl$default(null, 1, null);
                this.pendingRecalculateMarks = it;
            }
            PrioritySet.m4505addimpl(it, group);
        }
    }

    /* JADX INFO: renamed from: updateContainsMarkNow-qrM0pCk, reason: not valid java name */
    private final void m4520updateContainsMarkNowqrM0pCk(int group, MutableIntList set) {
        int groupAddress = groupIndexToAddress(group);
        boolean containsAnyMarks = childContainsAnyMarks(group);
        int[] $this$containsMark$iv = this.groups;
        boolean markChanges = (($this$containsMark$iv[(groupAddress * 5) + 1] & 67108864) != 0) != containsAnyMarks;
        if (markChanges) {
            SlotTableKt.updateContainsMark(this.groups, groupAddress, containsAnyMarks);
            int parent = parent(group);
            if (parent >= 0) {
                PrioritySet.m4505addimpl(set, parent);
            }
        }
    }

    private final boolean childContainsAnyMarks(int group) {
        int child = group + 1;
        int end = groupSize(group) + group;
        while (true) {
            if (child >= end) {
                return false;
            }
            int[] $this$containsAnyMark$iv = this.groups;
            int address$iv = groupIndexToAddress(child);
            if (($this$containsAnyMark$iv[(address$iv * 5) + 1] & 201326592) != 0) {
                return true;
            }
            child += groupSize(child);
        }
    }

    public final int anchorIndex(GapAnchor anchor) {
        int it = anchor.getLocation();
        return it < 0 ? getSize$runtime() + it : it;
    }

    public String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    private final void saveCurrentGroupEnd() {
        this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
    }

    private final int restoreCurrentGroupEnd() {
        int newGroupEnd = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.currentGroupEnd = newGroupEnd;
        return newGroupEnd;
    }

    private final void fixParentAnchorsFor(int parent, int endGroup, int firstChild) {
        int parentAnchor = parentIndexToAnchor(parent, this.groupGapStart);
        int child = firstChild;
        while (child < endGroup) {
            int[] $this$updateParentAnchor$iv = this.groups;
            int address$iv = groupIndexToAddress(child);
            $this$updateParentAnchor$iv[(address$iv * 5) + 2] = parentAnchor;
            int[] $this$updateParentAnchor$iv2 = this.groups;
            int childEnd = SlotTableKt.groupSize($this$updateParentAnchor$iv2, groupIndexToAddress(child)) + child;
            fixParentAnchorsFor(child, childEnd, child + 1);
            child = childEnd;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveGroupGapTo(int index) {
        int gapLen = this.groupGapLen;
        int gapStart = this.groupGapStart;
        if (gapStart != index) {
            if (!this.anchors.isEmpty()) {
                updateAnchors(gapStart, index);
            }
            if (gapLen > 0) {
                int[] groups = this.groups;
                int groupPhysicalAddress = index * 5;
                int groupPhysicalGapLen = gapLen * 5;
                int groupPhysicalGapStart = gapStart * 5;
                if (index < gapStart) {
                    ArraysKt.copyInto(groups, groups, groupPhysicalAddress + groupPhysicalGapLen, groupPhysicalAddress, groupPhysicalGapStart);
                } else {
                    ArraysKt.copyInto(groups, groups, groupPhysicalGapStart, groupPhysicalGapStart + groupPhysicalGapLen, groupPhysicalAddress + groupPhysicalGapLen);
                }
            }
            int groupAddress = index < gapStart ? index + gapLen : gapStart;
            int capacity = getCapacity();
            boolean value$iv = groupAddress < capacity;
            boolean value$iv$iv = value$iv;
            if (!value$iv$iv) {
                ComposerKt.composeImmediateRuntimeError("Check failed");
            }
            while (groupAddress < capacity) {
                int[] $this$parentAnchor$iv = this.groups;
                int address$iv = groupAddress;
                int oldAnchor = $this$parentAnchor$iv[(address$iv * 5) + 2];
                int oldIndex = parentAnchorToIndex(oldAnchor);
                int newAnchor = parentIndexToAnchor(oldIndex, index);
                if (newAnchor != oldAnchor) {
                    int[] $this$updateParentAnchor$iv = this.groups;
                    int address$iv2 = groupAddress;
                    $this$updateParentAnchor$iv[(address$iv2 * 5) + 2] = newAnchor;
                }
                groupAddress++;
                if (groupAddress == index) {
                    groupAddress += gapLen;
                }
            }
        }
        this.groupGapStart = index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveSlotGapTo(int index, int group) {
        int gapLen = this.slotsGapLen;
        int gapStart = this.slotsGapStart;
        int slotsGapOwner = this.slotsGapOwner;
        if (gapStart != index) {
            Object[] slots = this.slots;
            if (index < gapStart) {
                int destinationOffset$iv = index + gapLen;
                System.arraycopy(slots, index, slots, destinationOffset$iv, gapStart - index);
            } else {
                int startIndex$iv = gapStart + gapLen;
                int endIndex$iv = index + gapLen;
                System.arraycopy(slots, startIndex$iv, slots, gapStart, endIndex$iv - startIndex$iv);
            }
        }
        int newSlotsGapOwner = Math.min(group + 1, getSize$runtime());
        if (slotsGapOwner != newSlotsGapOwner) {
            int slotsSize = this.slots.length - gapLen;
            if (newSlotsGapOwner < slotsGapOwner) {
                int updateAddress = groupIndexToAddress(newSlotsGapOwner);
                int stopUpdateAddress = groupIndexToAddress(slotsGapOwner);
                int groupGapStart = this.groupGapStart;
                while (updateAddress < stopUpdateAddress) {
                    int[] $this$dataAnchor$iv = this.groups;
                    int address$iv = updateAddress;
                    int anchor = $this$dataAnchor$iv[(address$iv * 5) + 4];
                    boolean value$iv = anchor >= 0;
                    if (!value$iv) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a positive anchor");
                    }
                    int[] $this$updateDataAnchor$iv = this.groups;
                    int anchor$iv = -((slotsSize - anchor) + 1);
                    int address$iv2 = updateAddress;
                    $this$updateDataAnchor$iv[(address$iv2 * 5) + 4] = anchor$iv;
                    updateAddress++;
                    if (updateAddress == groupGapStart) {
                        updateAddress += this.groupGapLen;
                    }
                }
            } else {
                int updateAddress2 = groupIndexToAddress(slotsGapOwner);
                int stopUpdateAddress2 = groupIndexToAddress(newSlotsGapOwner);
                while (updateAddress2 < stopUpdateAddress2) {
                    int[] $this$dataAnchor$iv2 = this.groups;
                    int address$iv3 = updateAddress2;
                    int anchor2 = $this$dataAnchor$iv2[(address$iv3 * 5) + 4];
                    boolean value$iv2 = anchor2 < 0;
                    if (!value$iv2) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a negative anchor");
                    }
                    int[] $this$updateDataAnchor$iv2 = this.groups;
                    int anchor$iv2 = slotsSize + anchor2 + 1;
                    int address$iv4 = updateAddress2;
                    $this$updateDataAnchor$iv2[(address$iv4 * 5) + 4] = anchor$iv2;
                    updateAddress2++;
                    if (updateAddress2 == this.groupGapStart) {
                        updateAddress2 += this.groupGapLen;
                    }
                }
            }
            this.slotsGapOwner = newSlotsGapOwner;
        }
        this.slotsGapStart = index;
    }

    private final void clearSlotGap() {
        int slotsGapStart = this.slotsGapStart;
        int slotsGapEnd = this.slotsGapLen + slotsGapStart;
        ArraysKt.fill(this.slots, (Object) null, slotsGapStart, slotsGapEnd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertGroups(int size) {
        if (size > 0) {
            int currentGroup = this.currentGroup;
            moveGroupGapTo(currentGroup);
            int gapStart = this.groupGapStart;
            int gapLen = this.groupGapLen;
            int oldCapacity = this.groups.length / 5;
            int oldSize = oldCapacity - gapLen;
            if (gapLen < size) {
                int[] groups = this.groups;
                int newCapacity = Math.max(Math.max(oldCapacity * 2, oldSize + size), 32);
                int[] newGroups = new int[newCapacity * 5];
                int newGapLen = newCapacity - oldSize;
                int oldGapEndAddress = gapStart + gapLen;
                int newGapEndAddress = gapStart + newGapLen;
                ArraysKt.copyInto(groups, newGroups, 0, 0, gapStart * 5);
                ArraysKt.copyInto(groups, newGroups, newGapEndAddress * 5, oldGapEndAddress * 5, oldCapacity * 5);
                this.groups = newGroups;
                gapLen = newGapLen;
            }
            int currentEnd = this.currentGroupEnd;
            if (currentEnd >= gapStart) {
                this.currentGroupEnd = currentEnd + size;
            }
            this.groupGapStart = gapStart + size;
            this.groupGapLen = gapLen - size;
            int index = oldSize > 0 ? dataIndex(currentGroup + size) : 0;
            int anchor = dataIndexToDataAnchor(index, this.slotsGapOwner < gapStart ? 0 : this.slotsGapStart, this.slotsGapLen, this.slots.length);
            int i = gapStart + size;
            for (int groupAddress = gapStart; groupAddress < i; groupAddress++) {
                int[] $this$updateDataAnchor$iv = this.groups;
                int address$iv = groupAddress;
                $this$updateDataAnchor$iv[(address$iv * 5) + 4] = anchor;
            }
            int groupAddress2 = this.slotsGapOwner;
            if (groupAddress2 >= gapStart) {
                this.slotsGapOwner = groupAddress2 + size;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertSlots(int size, int group) {
        if (size > 0) {
            moveSlotGapTo(this.currentSlot, group);
            int gapStart = this.slotsGapStart;
            int gapLen = this.slotsGapLen;
            if (gapLen < size) {
                Object[] slots = this.slots;
                int oldCapacity = slots.length;
                int oldSize = oldCapacity - gapLen;
                int newCapacity = Math.max(Math.max(oldCapacity * 2, oldSize + size), 32);
                Object[] newData = new Object[newCapacity];
                for (int i = 0; i < newCapacity; i++) {
                    newData[i] = null;
                }
                int newGapLen = newCapacity - oldSize;
                int oldGapEndAddress = gapStart + gapLen;
                int newGapEndAddress = gapStart + newGapLen;
                System.arraycopy(slots, 0, newData, 0, gapStart - 0);
                System.arraycopy(slots, oldGapEndAddress, newData, newGapEndAddress, oldCapacity - oldGapEndAddress);
                this.slots = newData;
                gapLen = newGapLen;
            }
            int currentDataEnd = this.currentSlotEnd;
            if (currentDataEnd >= gapStart) {
                this.currentSlotEnd = currentDataEnd + size;
            }
            this.slotsGapStart = gapStart + size;
            this.slotsGapLen = gapLen - size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeGroups(int start, int len) {
        if (len > 0) {
            boolean anchorsRemoved = false;
            ArrayList<GapAnchor> arrayList = this.anchors;
            moveGroupGapTo(start);
            if (!arrayList.isEmpty()) {
                anchorsRemoved = removeAnchors(start, len, this.sourceInformationMap);
            }
            this.groupGapStart = start;
            int previousGapLen = this.groupGapLen;
            int newGapLen = previousGapLen + len;
            this.groupGapLen = newGapLen;
            int slotsGapOwner = this.slotsGapOwner;
            if (slotsGapOwner > start) {
                this.slotsGapOwner = Math.max(start, slotsGapOwner - len);
            }
            if (this.currentGroupEnd >= this.groupGapStart) {
                this.currentGroupEnd -= len;
            }
            int parent = this.parent;
            if (!containsGroupMark(parent)) {
                return anchorsRemoved;
            }
            updateContainsMark(parent);
            return anchorsRemoved;
        }
        return false;
    }

    public final GapGroupSourceInformation sourceInformationOf$runtime(int group) {
        GapAnchor anchor;
        HashMap<GapAnchor, GapGroupSourceInformation> map = this.sourceInformationMap;
        if (map == null || (anchor = tryAnchor$runtime(group)) == null) {
            return null;
        }
        return map.get(anchor);
    }

    public final GapAnchor tryAnchor$runtime(int group) {
        boolean z = false;
        if (group >= 0 && group < getSize$runtime()) {
            z = true;
        }
        if (z) {
            return SlotTableKt.find(this.anchors, group, getSize$runtime());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSlots(int start, int len, int group) {
        if (len > 0) {
            int gapLen = this.slotsGapLen;
            int removeEnd = start + len;
            moveSlotGapTo(removeEnd, group);
            this.slotsGapStart = start;
            this.slotsGapLen = gapLen + len;
            ArraysKt.fill(this.slots, (Object) null, start, start + len);
            int currentDataEnd = this.currentSlotEnd;
            if (currentDataEnd >= start) {
                this.currentSlotEnd = currentDataEnd - len;
            }
        }
    }

    private final void updateNodeOfGroup(int index, Object value) {
        int address = groupIndexToAddress(index);
        boolean value$iv = false;
        if (address < this.groups.length) {
            int[] $this$isNode$iv = this.groups;
            if (($this$isNode$iv[(address * 5) + 1] & 1073741824) != 0) {
                value$iv = true;
            }
        }
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Updating the node of a group at " + index + " that was not created with as a node group");
        }
        this.slots[dataIndexToDataAddress(nodeIndex(this.groups, address))] = value;
    }

    private final void updateAnchors(int previousGapStart, int newGapStart) {
        GapAnchor anchor;
        int location;
        GapAnchor anchor2;
        int location2;
        int gapLen = this.groupGapLen;
        int size = getCapacity() - gapLen;
        ArrayList<GapAnchor> arrayList = this.anchors;
        if (previousGapStart < newGapStart) {
            for (int index = SlotTableKt.locationOf(arrayList, previousGapStart, size); index < this.anchors.size() && (location2 = (anchor2 = this.anchors.get(index)).getLocation()) < 0; index++) {
                int newLocation = size + location2;
                if (newLocation < newGapStart) {
                    anchor2.setLocation$runtime(size + location2);
                } else {
                    return;
                }
            }
            return;
        }
        for (int index2 = SlotTableKt.locationOf(arrayList, newGapStart, size); index2 < this.anchors.size() && (location = (anchor = this.anchors.get(index2)).getLocation()) >= 0; index2++) {
            anchor.setLocation$runtime(-(size - location));
        }
    }

    private final boolean removeAnchors(int gapStart, int size, HashMap<GapAnchor, GapGroupSourceInformation> sourceInformationMap) {
        int gapLen = this.groupGapLen;
        int removeEnd = gapStart + size;
        int groupsSize = getCapacity() - gapLen;
        int it = SlotTableKt.locationOf(this.anchors, gapStart + size, groupsSize);
        if (it >= this.anchors.size()) {
            it--;
        }
        int removeAnchorEnd = 0;
        int removeAnchorStart = it + 1;
        while (it >= 0) {
            GapAnchor anchor = this.anchors.get(it);
            int location = anchorIndex(anchor);
            if (location < gapStart) {
                break;
            }
            if (location < removeEnd) {
                anchor.setLocation$runtime(Integer.MIN_VALUE);
                if (sourceInformationMap != null) {
                    sourceInformationMap.remove(anchor);
                }
                removeAnchorStart = it;
                if (removeAnchorEnd == 0) {
                    removeAnchorEnd = it + 1;
                }
            }
            it--;
        }
        boolean it2 = removeAnchorStart < removeAnchorEnd;
        if (it2) {
            this.anchors.subList(removeAnchorStart, removeAnchorEnd).clear();
        }
        return it2;
    }

    private final void moveAnchors(int originalLocation, int newLocation, int size) {
        GapAnchor anchor;
        int location;
        int end = originalLocation + size;
        int groupsSize = getSize$runtime();
        int index = SlotTableKt.locationOf(this.anchors, originalLocation, groupsSize);
        List removedAnchors = new ArrayList();
        if (index >= 0) {
            while (index < this.anchors.size() && (location = anchorIndex((anchor = this.anchors.get(index)))) >= originalLocation && location < end) {
                removedAnchors.add(anchor);
                this.anchors.remove(index);
            }
        }
        int moveDelta = newLocation - originalLocation;
        int index$iv = 0;
        int size2 = removedAnchors.size();
        while (index$iv < size2) {
            Object item$iv = removedAnchors.get(index$iv);
            GapAnchor anchor2 = (GapAnchor) item$iv;
            int anchorIndex = anchorIndex(anchor2);
            int newAnchorIndex = anchorIndex + moveDelta;
            if (newAnchorIndex >= this.groupGapStart) {
                anchor2.setLocation$runtime(-(groupsSize - newAnchorIndex));
            } else {
                anchor2.setLocation$runtime(newAnchorIndex);
            }
            int insertIndex = SlotTableKt.locationOf(this.anchors, newAnchorIndex, groupsSize);
            this.anchors.add(insertIndex, anchor2);
            index$iv++;
            end = end;
        }
    }

    public final String toDebugString() {
        StringBuilder $this$toDebugString_u24lambda_u240 = new StringBuilder();
        $this$toDebugString_u24lambda_u240.append(toString()).append('\n');
        $this$toDebugString_u24lambda_u240.append("  parent:    " + this.parent).append('\n');
        $this$toDebugString_u24lambda_u240.append("  current:   " + this.currentGroup).append('\n');
        $this$toDebugString_u24lambda_u240.append("  group gap: " + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + '(' + this.groupGapLen + ')').append('\n');
        $this$toDebugString_u24lambda_u240.append("  slots gap: " + this.slotsGapStart + '-' + (this.slotsGapStart + this.slotsGapLen) + '(' + this.slotsGapLen + ')').append('\n');
        $this$toDebugString_u24lambda_u240.append("  gap owner: " + this.slotsGapOwner).append('\n');
        int size$runtime = getSize$runtime();
        for (int index = 0; index < size$runtime; index++) {
            groupAsString($this$toDebugString_u24lambda_u240, index);
            $this$toDebugString_u24lambda_u240.append('\n');
        }
        return $this$toDebugString_u24lambda_u240.toString();
    }

    private final void groupAsString(StringBuilder $this$groupAsString, int index) {
        int address = groupIndexToAddress(index);
        $this$groupAsString.append("Group(");
        if (index < 10) {
            $this$groupAsString.append(' ');
        }
        if (index < 100) {
            $this$groupAsString.append(' ');
        }
        if (index < 1000) {
            $this$groupAsString.append(' ');
        }
        $this$groupAsString.append(index);
        if (address != index) {
            $this$groupAsString.append("(");
            $this$groupAsString.append(address);
            $this$groupAsString.append(")");
        }
        $this$groupAsString.append('#');
        $this$groupAsString.append(SlotTableKt.groupSize(this.groups, address));
        $this$groupAsString.append('^');
        int[] $this$parentAnchor$iv = this.groups;
        $this$groupAsString.append(parentAnchorToIndex($this$parentAnchor$iv[(address * 5) + 2]));
        $this$groupAsString.append(": key=");
        int[] $this$key$iv = this.groups;
        $this$groupAsString.append($this$key$iv[address * 5]);
        $this$groupAsString.append(", nodes=");
        int[] $this$nodeCount$iv = this.groups;
        $this$groupAsString.append($this$nodeCount$iv[(address * 5) + 1] & 67108863);
        $this$groupAsString.append(", dataAnchor=");
        int[] $this$dataAnchor$iv = this.groups;
        $this$groupAsString.append($this$dataAnchor$iv[(address * 5) + 4]);
        $this$groupAsString.append(", parentAnchor=");
        int[] $this$parentAnchor$iv2 = this.groups;
        $this$groupAsString.append($this$parentAnchor$iv2[(address * 5) + 2]);
        int[] $this$isNode$iv = this.groups;
        if (($this$isNode$iv[(address * 5) + 1] & 1073741824) != 0) {
            $this$groupAsString.append(", node=" + SlotTableKt.summarize(String.valueOf(this.slots[dataIndexToDataAddress(nodeIndex(this.groups, address))]), 10));
        }
        int startData = slotIndex(this.groups, address);
        int successorAddress = groupIndexToAddress(index + 1);
        int endData = dataIndex(this.groups, successorAddress);
        if (endData > startData) {
            $this$groupAsString.append(", [");
            for (int dataIndex = startData; dataIndex < endData; dataIndex++) {
                if (dataIndex != startData) {
                    $this$groupAsString.append(", ");
                }
                int dataAddress = dataIndexToDataAddress(dataIndex);
                $this$groupAsString.append(SlotTableKt.summarize(String.valueOf(this.slots[dataAddress]), 10));
            }
            $this$groupAsString.append(']');
        }
        $this$groupAsString.append(")");
    }

    public final void verifyDataAnchors$runtime() {
        int previousDataIndex = 0;
        int owner = this.slotsGapOwner;
        boolean ownerFound = false;
        int slotsSize = this.slots.length - this.slotsGapLen;
        int index = 0;
        int size$runtime = getSize$runtime();
        while (index < size$runtime) {
            int address = groupIndexToAddress(index);
            int[] $this$dataAnchor$iv = this.groups;
            int dataAnchor = $this$dataAnchor$iv[(address * 5) + 4];
            int dataIndex = dataIndex(this.groups, address);
            boolean value$iv = dataIndex >= previousDataIndex;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Data index out of order at " + index + ", previous = " + previousDataIndex + ", current = " + dataIndex);
            }
            boolean value$iv2 = dataIndex <= slotsSize;
            if (!value$iv2) {
                PreconditionsKt.throwIllegalStateException("Data index, " + dataIndex + ", out of bound at " + index);
            }
            if (dataAnchor < 0 && !ownerFound) {
                boolean value$iv3 = owner == index;
                if (!value$iv3) {
                    PreconditionsKt.throwIllegalStateException("Expected the slot gap owner to be " + owner + " found gap at " + index);
                }
                ownerFound = true;
            }
            previousDataIndex = dataIndex;
            index++;
        }
    }

    public final void verifyParentAnchors$runtime() {
        int gapStart = this.groupGapStart;
        int gapLen = this.groupGapLen;
        int capacity = getCapacity();
        int groupAddress = 0;
        while (true) {
            if (groupAddress >= gapStart) {
                break;
            }
            int[] $this$parentAnchor$iv = this.groups;
            int address$iv = groupAddress;
            boolean value$iv = $this$parentAnchor$iv[(address$iv * 5) + 2] > -2;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Expected a start relative anchor at " + groupAddress);
            }
            groupAddress++;
        }
        for (int groupAddress2 = gapStart + gapLen; groupAddress2 < capacity; groupAddress2++) {
            int[] $this$parentAnchor$iv2 = this.groups;
            int address$iv2 = groupAddress2;
            int parentAnchor = $this$parentAnchor$iv2[(address$iv2 * 5) + 2];
            int parentIndex = parentAnchorToIndex(parentAnchor);
            if (parentIndex < gapStart) {
                boolean value$iv2 = parentAnchor > -2;
                if (!value$iv2) {
                    PreconditionsKt.throwIllegalStateException("Expected a start relative anchor at " + groupAddress2);
                }
            } else {
                boolean value$iv3 = parentAnchor <= -2;
                if (!value$iv3) {
                    PreconditionsKt.throwIllegalStateException("Expected an end relative anchor at " + groupAddress2);
                }
            }
        }
    }

    public final int getSize$runtime() {
        return getCapacity() - this.groupGapLen;
    }

    private final int getCapacity() {
        return this.groups.length / 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int groupIndexToAddress(int index) {
        return (this.groupGapLen * (index < this.groupGapStart ? 0 : 1)) + index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int dataIndex) {
        return (this.slotsGapLen * (dataIndex < this.slotsGapStart ? 0 : 1)) + dataIndex;
    }

    private final int parent(int[] $this$parent, int index) {
        int address$iv = groupIndexToAddress(index);
        return parentAnchorToIndex($this$parent[(address$iv * 5) + 2]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int index) {
        return dataIndex(this.groups, groupIndexToAddress(index));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int[] $this$dataIndex, int address) {
        if (address >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        int address$iv = $this$dataIndex[(address * 5) + 4];
        return dataAnchorToDataIndex(address$iv, this.slotsGapLen, this.slots.length);
    }

    private final int slotIndex(int[] $this$slotIndex, int address) {
        return address >= getCapacity() ? this.slots.length - this.slotsGapLen : dataAnchorToDataIndex(SlotTableKt.slotAnchor($this$slotIndex, address), this.slotsGapLen, this.slots.length);
    }

    private final void updateDataIndex(int[] $this$updateDataIndex, int address, int dataIndex) {
        int anchor$iv = dataIndexToDataAnchor(dataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length);
        $this$updateDataIndex[(address * 5) + 4] = anchor$iv;
    }

    private final int nodeIndex(int[] $this$nodeIndex, int address) {
        return dataIndex($this$nodeIndex, address);
    }

    private final int auxIndex(int[] $this$auxIndex, int address) {
        int iDataIndex = dataIndex($this$auxIndex, address);
        int address$iv = $this$auxIndex[(address * 5) + 1];
        int value$iv = address$iv >> 29;
        return iDataIndex + Integer.bitCount(value$iv);
    }

    private final List<Integer> dataIndexes(int[] $this$dataIndexes) {
        List it = SlotTableKt.dataAnchors$default(this.groups, 0, 1, null);
        List $this$fastMap$iv = CollectionsKt.plus((Collection) CollectionsKt.slice(it, RangesKt.until(0, this.groupGapStart)), (Iterable) CollectionsKt.slice(it, RangesKt.until(this.groupGapStart + this.groupGapLen, $this$dataIndexes.length / 5)));
        int $i$f$fastMap = 0;
        ArrayList target$iv = new ArrayList($this$fastMap$iv.size());
        int index$iv$iv = 0;
        int size = $this$fastMap$iv.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
            int anchor = ((Number) item$iv$iv).intValue();
            target$iv.add(Integer.valueOf(dataAnchorToDataIndex(anchor, this.slotsGapLen, this.slots.length)));
            index$iv$iv++;
            $i$f$fastMap = $i$f$fastMap;
        }
        return target$iv;
    }

    private final List<Integer> keys() {
        List $this$fastFilterIndexed$iv = SlotTableKt.keys$default(this.groups, 0, 1, null);
        List target$iv = new ArrayList($this$fastFilterIndexed$iv.size());
        int size = $this$fastFilterIndexed$iv.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = $this$fastFilterIndexed$iv.get(index$iv$iv);
            int index$iv = index$iv$iv;
            ((Number) item$iv$iv).intValue();
            if (index$iv < this.groupGapStart || index$iv >= this.groupGapStart + this.groupGapLen) {
                target$iv.add(item$iv$iv);
            }
        }
        return target$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAnchor(int index, int gapStart, int gapLen, int capacity) {
        return index > gapStart ? -(((capacity - gapLen) - index) + 1) : index;
    }

    private final int dataAnchorToDataIndex(int anchor, int gapLen, int capacity) {
        return anchor < 0 ? (capacity - gapLen) + anchor + 1 : anchor;
    }

    private final int parentIndexToAnchor(int index, int gapStart) {
        return index < gapStart ? index : -((getSize$runtime() - index) + 2);
    }

    private final int parentAnchorToIndex(int index) {
        return index > -2 ? index : (getSize$runtime() + index) - (-2);
    }
}
