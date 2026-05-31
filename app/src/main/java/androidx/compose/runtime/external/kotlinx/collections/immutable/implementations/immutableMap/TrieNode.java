package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: TrieNode.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0001\u0018\u0000 ~*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0002}~B1\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\u000b\u0010\fB)\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b¢\u0006\u0004\b\u000b\u0010\rJ\u0014\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\u0002J\u0014\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\u0002J\r\u0010\u0015\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0016J\u0015\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001aJ\u0010\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0015\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001dJ\u0015\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001fJ\u0015\u0010 \u001a\u00028\u00002\u0006\u0010!\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00028\u00012\u0006\u0010!\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\"J!\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u0005H\u0000¢\u0006\u0002\b%J1\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010(J9\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010*\u001a\u00020\nH\u0002¢\u0006\u0002\u0010+J)\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010-J=\u0010.\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u00101J8\u00102\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002J8\u00104\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010*\u001a\u00020\nH\u0002J&\u00105\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J.\u00106\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002JO\u00107\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010<JI\u0010=\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010>JQ\u0010?\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002¢\u0006\u0002\u0010@J[\u0010A\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010B\u001a\u00020\u00052\u0006\u0010C\u001a\u00028\u00002\u0006\u0010D\u001a\u00028\u00012\u0006\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00028\u00002\u0006\u0010G\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\b\u0010*\u001a\u0004\u0018\u00010\nH\u0002¢\u0006\u0002\u0010HJ&\u0010I\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J:\u0010J\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010!\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\u001e\u0010K\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010L\u001a\u00020\u0005H\u0002J2\u0010M\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010L\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\u0015\u0010N\u001a\u00020\u00182\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010OJ\u0017\u0010P\u001a\u0004\u0018\u00018\u00012\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010QJ+\u0010R\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f2\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010SJ=\u0010T\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010UJ#\u0010V\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010WJ7\u0010X\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010YJ+\u0010V\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010ZJ?\u0010X\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002¢\u0006\u0002\u0010UJ8\u0010[\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010]\u001a\u00020^2\u0006\u0010*\u001a\u00020\nH\u0002JT\u0010_\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u00052\u0006\u0010]\u001a\u00020^2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0002J\b\u0010`\u001a\u00020\u0005H\u0002J\u001c\u0010a\u001a\u00020\u00182\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000H\u0002J#\u0010b\u001a\u00020\u00182\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010dJ%\u0010e\u001a\u0004\u0018\u00018\u00012\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010fJJ\u0010g\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0012\u0010\\\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010;\u001a\u00020\u00052\u0006\u0010]\u001a\u00020^2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100J9\u0010h\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f2\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010iJK\u0010j\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010kJ1\u0010l\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010mJP\u0010n\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0005H\u0002JE\u0010p\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010qJX\u0010r\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0012\u0010o\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0014\u00103\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010*\u001a\u00020\nH\u0002J9\u0010l\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u0005¢\u0006\u0002\u0010sJM\u0010p\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00002\u0006\u0010c\u001a\u00020\u00052\u0006\u0010'\u001a\u00028\u00002\u0006\u0010\u0011\u001a\u00028\u00012\u0006\u0010;\u001a\u00020\u00052\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100¢\u0006\u0002\u0010kJ\u0091\u0001\u0010t\u001a\u00020u2\u0081\u0001\u0010v\u001a}\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(z\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b({\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020u0wH\u0000¢\u0006\u0002\b|J\u009c\u0001\u0010t\u001a\u00020u2\u0081\u0001\u0010v\u001a}\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0000¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(z\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(;\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b({\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(\u0004\u0012\u0013\u0012\u00110\u0005¢\u0006\f\bx\u0012\b\by\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020u0w2\u0006\u0010{\u001a\u00020\u00052\u0006\u0010;\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R0\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b2\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\b@BX\u0080\u000e¢\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u007f"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "K", "V", "", "dataMap", "", "nodeMap", "buffer", "", "ownedBy", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "<init>", "(II[Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)V", "(II[Ljava/lang/Object;)V", "asInsertResult", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "asUpdateResult", "value", "getBuffer$runtime", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "entryCount", "entryCount$runtime", "hasEntryAt", "", "positionMask", "hasEntryAt$runtime", "hasNodeAt", "entryKeyIndex", "entryKeyIndex$runtime", "nodeIndex", "nodeIndex$runtime", "keyAtIndex", "keyIndex", "(I)Ljava/lang/Object;", "valueAtKeyIndex", "nodeAtIndex", "nodeAtIndex$runtime", "insertEntryAt", "key", "(ILjava/lang/Object;Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableInsertEntryAt", "owner", "(ILjava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "updateValueAtIndex", "(ILjava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableUpdateValueAtIndex", "mutator", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;", "(ILjava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "updateNodeAtIndex", "newNode", "mutableUpdateNodeAtIndex", "removeNodeAtIndex", "mutableRemoveNodeAtIndex", "bufferMoveEntryToNode", "newKeyHash", "newKey", "newValue", "shift", "(IIILjava/lang/Object;Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)[Ljava/lang/Object;", "moveEntryToNode", "(IIILjava/lang/Object;Ljava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableMoveEntryToNode", "(IIILjava/lang/Object;Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "makeNode", "keyHash1", "key1", "value1", "keyHash2", "key2", "value2", "(ILjava/lang/Object;Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "removeEntryAtIndex", "mutableRemoveEntryAtIndex", "collisionRemoveEntryAtIndex", "i", "mutableCollisionRemoveEntryAtIndex", "collisionContainsKey", "(Ljava/lang/Object;)Z", "collisionGet", "(Ljava/lang/Object;)Ljava/lang/Object;", "collisionPut", "(Ljava/lang/Object;Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "mutableCollisionPut", "(Ljava/lang/Object;Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "collisionRemove", "(Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableCollisionRemove", "(Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "(Ljava/lang/Object;Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableCollisionPutAll", "otherNode", "intersectionCounter", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/DeltaCounter;", "mutablePutAllFromOtherNodeCell", "calculateSize", "elementsIdentityEquals", "containsKey", "keyHash", "(ILjava/lang/Object;I)Z", "get", "(ILjava/lang/Object;I)Ljava/lang/Object;", "mutablePutAll", "put", "(ILjava/lang/Object;Ljava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "mutablePut", "(ILjava/lang/Object;Ljava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "remove", "(ILjava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "replaceNode", "targetNode", "mutableRemove", "(ILjava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/PersistentHashMapBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "mutableReplaceNode", "(ILjava/lang/Object;Ljava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "accept", "", "visitor", "Lkotlin/Function5;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "node", "hash", "accept$runtime", "ModificationResult", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TrieNode<K, V> {
    private Object[] buffer;
    private int dataMap;
    private int nodeMap;
    private final MutabilityOwnership ownedBy;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final TrieNode EMPTY = new TrieNode(0, 0, new Object[0]);

    public TrieNode(int dataMap, int nodeMap, Object[] buffer, MutabilityOwnership ownedBy) {
        this.dataMap = dataMap;
        this.nodeMap = nodeMap;
        this.ownedBy = ownedBy;
        this.buffer = buffer;
    }

    public TrieNode(int dataMap, int nodeMap, Object[] buffer) {
        this(dataMap, nodeMap, buffer, null);
    }

    /* JADX INFO: compiled from: TrieNode.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B#\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJA\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00002*\u0010\u0011\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00050\u0012H\u0086\bR&\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0013"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$ModificationResult;", "K", "V", "", "node", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "sizeDelta", "", "<init>", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;I)V", "getNode", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "setNode", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;)V", "getSizeDelta", "()I", "replaceNode", "operation", "Lkotlin/Function1;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ModificationResult<K, V> {
        public static final int $stable = 8;
        private TrieNode<K, V> node;
        private final int sizeDelta;

        public ModificationResult(TrieNode<K, V> trieNode, int sizeDelta) {
            this.node = trieNode;
            this.sizeDelta = sizeDelta;
        }

        public final TrieNode<K, V> getNode() {
            return this.node;
        }

        public final int getSizeDelta() {
            return this.sizeDelta;
        }

        public final void setNode(TrieNode<K, V> trieNode) {
            this.node = trieNode;
        }

        public final ModificationResult<K, V> replaceNode(Function1<? super TrieNode<K, V>, TrieNode<K, V>> operation) {
            ModificationResult<K, V> modificationResult = this;
            modificationResult.setNode(operation.invoke(modificationResult.getNode()));
            return this;
        }
    }

    private final ModificationResult<K, V> asInsertResult() {
        return new ModificationResult<>(this, 1);
    }

    private final ModificationResult<K, V> asUpdateResult() {
        return new ModificationResult<>(this, 0);
    }

    /* JADX INFO: renamed from: getBuffer$runtime, reason: from getter */
    public final Object[] getBuffer() {
        return this.buffer;
    }

    public final int entryCount$runtime() {
        return Integer.bitCount(this.dataMap);
    }

    public final boolean hasEntryAt$runtime(int positionMask) {
        return (this.dataMap & positionMask) != 0;
    }

    private final boolean hasNodeAt(int positionMask) {
        return (this.nodeMap & positionMask) != 0;
    }

    public final int entryKeyIndex$runtime(int positionMask) {
        return Integer.bitCount(this.dataMap & (positionMask - 1)) * 2;
    }

    public final int nodeIndex$runtime(int positionMask) {
        return (this.buffer.length - 1) - Integer.bitCount(this.nodeMap & (positionMask - 1));
    }

    private final K keyAtIndex(int keyIndex) {
        return (K) this.buffer[keyIndex];
    }

    private final V valueAtKeyIndex(int keyIndex) {
        return (V) this.buffer[keyIndex + 1];
    }

    public final TrieNode<K, V> nodeAtIndex$runtime(int nodeIndex) {
        Object obj = this.buffer[nodeIndex];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode>");
        return (TrieNode) obj;
    }

    private final TrieNode<K, V> insertEntryAt(int positionMask, K key, V value) {
        int keyIndex = entryKeyIndex$runtime(positionMask);
        Object[] newBuffer = TrieNodeKt.insertEntryAtIndex(this.buffer, keyIndex, key, value);
        return new TrieNode<>(this.dataMap | positionMask, this.nodeMap, newBuffer);
    }

    private final TrieNode<K, V> mutableInsertEntryAt(int positionMask, K key, V value, MutabilityOwnership owner) {
        int keyIndex = entryKeyIndex$runtime(positionMask);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            this.buffer = TrieNodeKt.insertEntryAtIndex(objArr, keyIndex, key, value);
            this.dataMap |= positionMask;
            return this;
        }
        Object[] newBuffer = TrieNodeKt.insertEntryAtIndex(objArr, keyIndex, key, value);
        return new TrieNode<>(this.dataMap | positionMask, this.nodeMap, newBuffer, owner);
    }

    private final TrieNode<K, V> updateValueAtIndex(int keyIndex, V value) {
        Object[] objArr = this.buffer;
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        newBuffer[keyIndex + 1] = value;
        return new TrieNode<>(this.dataMap, this.nodeMap, newBuffer);
    }

    private final TrieNode<K, V> mutableUpdateValueAtIndex(int keyIndex, V value, PersistentHashMapBuilder<K, V> mutator) {
        if (this.ownedBy == mutator.getOwnership()) {
            this.buffer[keyIndex + 1] = value;
            return this;
        }
        mutator.setModCount$runtime(mutator.getModCount() + 1);
        Object[] objArr = this.buffer;
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        newBuffer[keyIndex + 1] = value;
        return new TrieNode<>(this.dataMap, this.nodeMap, newBuffer, mutator.getOwnership());
    }

    private final TrieNode<K, V> updateNodeAtIndex(int nodeIndex, int positionMask, TrieNode<K, V> newNode) {
        Object[] newNodeBuffer = newNode.buffer;
        if (newNodeBuffer.length == 2 && newNode.nodeMap == 0) {
            if (this.buffer.length == 1) {
                newNode.dataMap = this.nodeMap;
                return newNode;
            }
            int keyIndex = entryKeyIndex$runtime(positionMask);
            return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap ^ positionMask, TrieNodeKt.replaceNodeWithEntry(this.buffer, nodeIndex, keyIndex, newNodeBuffer[0], newNodeBuffer[1]));
        }
        Object[] newBuffer = Arrays.copyOf(this.buffer, this.buffer.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        newBuffer[nodeIndex] = newNode;
        return new TrieNode<>(this.dataMap, this.nodeMap, newBuffer);
    }

    private final TrieNode<K, V> mutableUpdateNodeAtIndex(int nodeIndex, TrieNode<K, V> newNode, MutabilityOwnership owner) {
        if (this.buffer.length == 1 && newNode.buffer.length == 2 && newNode.nodeMap == 0) {
            newNode.dataMap = this.nodeMap;
            return newNode;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            objArr[nodeIndex] = newNode;
            return this;
        }
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
        newBuffer[nodeIndex] = newNode;
        return new TrieNode<>(this.dataMap, this.nodeMap, newBuffer, owner);
    }

    private final TrieNode<K, V> removeNodeAtIndex(int nodeIndex, int positionMask) {
        if (this.buffer.length == 1) {
            return null;
        }
        Object[] newBuffer = TrieNodeKt.removeNodeAtIndex(this.buffer, nodeIndex);
        return new TrieNode<>(this.dataMap, this.nodeMap ^ positionMask, newBuffer);
    }

    private final TrieNode<K, V> mutableRemoveNodeAtIndex(int nodeIndex, int positionMask, MutabilityOwnership owner) {
        if (this.buffer.length == 1) {
            return null;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            this.buffer = TrieNodeKt.removeNodeAtIndex(objArr, nodeIndex);
            this.nodeMap ^= positionMask;
            return this;
        }
        Object[] newBuffer = TrieNodeKt.removeNodeAtIndex(objArr, nodeIndex);
        return new TrieNode<>(this.dataMap, this.nodeMap ^ positionMask, newBuffer, owner);
    }

    private final Object[] bufferMoveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift, MutabilityOwnership owner) {
        K kKeyAtIndex = keyAtIndex(keyIndex);
        int storedKeyHash = kKeyAtIndex != null ? kKeyAtIndex.hashCode() : 0;
        TrieNode<K, V> trieNodeMakeNode = makeNode(storedKeyHash, kKeyAtIndex, valueAtKeyIndex(keyIndex), newKeyHash, newKey, newValue, shift + 5, owner);
        int nodeIndex = nodeIndex$runtime(positionMask) + 1;
        return TrieNodeKt.replaceEntryWithNode(this.buffer, keyIndex, nodeIndex, trieNodeMakeNode);
    }

    private final TrieNode<K, V> moveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift) {
        Object[] newBuffer = bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, null);
        return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap | positionMask, newBuffer);
    }

    private final TrieNode<K, V> mutableMoveEntryToNode(int keyIndex, int positionMask, int newKeyHash, K newKey, V newValue, int shift, MutabilityOwnership owner) {
        if (this.ownedBy == owner) {
            this.buffer = bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, owner);
            this.dataMap ^= positionMask;
            this.nodeMap |= positionMask;
            return this;
        }
        Object[] newBuffer = bufferMoveEntryToNode(keyIndex, positionMask, newKeyHash, newKey, newValue, shift, owner);
        return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap | positionMask, newBuffer, owner);
    }

    private final TrieNode<K, V> makeNode(int keyHash1, K key1, V value1, int keyHash2, K key2, V value2, int shift, MutabilityOwnership owner) {
        Object[] nodeBuffer;
        if (shift > 30) {
            return new TrieNode<>(0, 0, new Object[]{key1, value1, key2, value2}, owner);
        }
        int setBit1 = TrieNodeKt.indexSegment(keyHash1, shift);
        int setBit2 = TrieNodeKt.indexSegment(keyHash2, shift);
        if (setBit1 != setBit2) {
            if (setBit1 < setBit2) {
                nodeBuffer = new Object[]{key1, value1, key2, value2};
            } else {
                nodeBuffer = new Object[]{key2, value2, key1, value1};
            }
            return new TrieNode<>((1 << setBit1) | (1 << setBit2), 0, nodeBuffer, owner);
        }
        return new TrieNode<>(0, 1 << setBit1, new Object[]{makeNode(keyHash1, key1, value1, keyHash2, key2, value2, shift + 5, owner)}, owner);
    }

    private final TrieNode<K, V> removeEntryAtIndex(int keyIndex, int positionMask) {
        if (this.buffer.length == 2) {
            return null;
        }
        Object[] newBuffer = TrieNodeKt.removeEntryAtIndex(this.buffer, keyIndex);
        return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap, newBuffer);
    }

    private final TrieNode<K, V> mutableRemoveEntryAtIndex(int keyIndex, int positionMask, PersistentHashMapBuilder<K, V> mutator) {
        mutator.setSize(mutator.size() - 1);
        mutator.setOperationResult$runtime(valueAtKeyIndex(keyIndex));
        if (this.buffer.length == 2) {
            return null;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        MutabilityOwnership ownership = mutator.getOwnership();
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == ownership) {
            this.buffer = TrieNodeKt.removeEntryAtIndex(objArr, keyIndex);
            this.dataMap ^= positionMask;
            return this;
        }
        Object[] newBuffer = TrieNodeKt.removeEntryAtIndex(objArr, keyIndex);
        return new TrieNode<>(this.dataMap ^ positionMask, this.nodeMap, newBuffer, mutator.getOwnership());
    }

    private final TrieNode<K, V> collisionRemoveEntryAtIndex(int i) {
        if (this.buffer.length == 2) {
            return null;
        }
        Object[] newBuffer = TrieNodeKt.removeEntryAtIndex(this.buffer, i);
        return new TrieNode<>(0, 0, newBuffer);
    }

    private final TrieNode<K, V> mutableCollisionRemoveEntryAtIndex(int i, PersistentHashMapBuilder<K, V> mutator) {
        mutator.setSize(mutator.size() - 1);
        mutator.setOperationResult$runtime(valueAtKeyIndex(i));
        if (this.buffer.length == 2) {
            return null;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        MutabilityOwnership ownership = mutator.getOwnership();
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == ownership) {
            this.buffer = TrieNodeKt.removeEntryAtIndex(objArr, i);
            return this;
        }
        Object[] newBuffer = TrieNodeKt.removeEntryAtIndex(objArr, i);
        return new TrieNode<>(0, 0, newBuffer, mutator.getOwnership());
    }

    private final boolean collisionContainsKey(K key) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && i <= last) || (step < 0 && last <= i)) {
            while (!Intrinsics.areEqual(key, this.buffer[i])) {
                if (i != last) {
                    i += step;
                }
            }
            return true;
        }
        return false;
    }

    private final V collisionGet(K key) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && i <= last) || (step < 0 && last <= i)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(i))) {
                if (i == last) {
                    return null;
                }
                i += step;
            }
            return valueAtKeyIndex(i);
        }
        return null;
    }

    private final ModificationResult<K, V> collisionPut(K key, V value) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && i <= last) || (step < 0 && last <= i)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(i))) {
                if (i != last) {
                    i += step;
                }
            }
            if (value == valueAtKeyIndex(i)) {
                return null;
            }
            Object[] objArr = this.buffer;
            Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
            Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
            newBuffer[i + 1] = value;
            return new TrieNode(0, 0, newBuffer).asUpdateResult();
        }
        return new TrieNode(0, 0, TrieNodeKt.insertEntryAtIndex(this.buffer, 0, key, value)).asInsertResult();
    }

    private final TrieNode<K, V> mutableCollisionPut(K key, V value, PersistentHashMapBuilder<K, V> mutator) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && i <= last) || (step < 0 && last <= i)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(i))) {
                if (i != last) {
                    i += step;
                }
            }
            mutator.setOperationResult$runtime(valueAtKeyIndex(i));
            if (this.ownedBy == mutator.getOwnership()) {
                this.buffer[i + 1] = value;
                return this;
            }
            mutator.setModCount$runtime(mutator.getModCount() + 1);
            Object[] objArr = this.buffer;
            Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
            Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(...)");
            newBuffer[i + 1] = value;
            return new TrieNode<>(0, 0, newBuffer, mutator.getOwnership());
        }
        mutator.setSize(mutator.size() + 1);
        return new TrieNode<>(0, 0, TrieNodeKt.insertEntryAtIndex(this.buffer, 0, key, value), mutator.getOwnership());
    }

    private final TrieNode<K, V> collisionRemove(K key) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && i <= last) || (step < 0 && last <= i)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(i))) {
                if (i != last) {
                    i += step;
                }
            }
            return collisionRemoveEntryAtIndex(i);
        }
        return this;
    }

    private final TrieNode<K, V> mutableCollisionRemove(K key, PersistentHashMapBuilder<K, V> mutator) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && i <= last) || (step < 0 && last <= i)) {
            while (!Intrinsics.areEqual(key, keyAtIndex(i))) {
                if (i != last) {
                    i += step;
                }
            }
            return mutableCollisionRemoveEntryAtIndex(i, mutator);
        }
        return this;
    }

    private final TrieNode<K, V> collisionRemove(K key, V value) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && i <= last) || (step < 0 && last <= i)) {
            while (true) {
                if (!Intrinsics.areEqual(key, keyAtIndex(i)) || !Intrinsics.areEqual(value, valueAtKeyIndex(i))) {
                    if (i == last) {
                        break;
                    }
                    i += step;
                } else {
                    return collisionRemoveEntryAtIndex(i);
                }
            }
        }
        return this;
    }

    private final TrieNode<K, V> mutableCollisionRemove(K key, V value, PersistentHashMapBuilder<K, V> mutator) {
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, this.buffer.length), 2);
        int i = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && i <= last) || (step < 0 && last <= i)) {
            while (true) {
                if (!Intrinsics.areEqual(key, keyAtIndex(i)) || !Intrinsics.areEqual(value, valueAtKeyIndex(i))) {
                    if (i == last) {
                        break;
                    }
                    i += step;
                } else {
                    return mutableCollisionRemoveEntryAtIndex(i, mutator);
                }
            }
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final TrieNode<K, V> mutableCollisionPutAll(TrieNode<K, V> otherNode, DeltaCounter intersectionCounter, MutabilityOwnership owner) {
        CommonFunctionsKt.m4649assert(this.nodeMap == 0);
        CommonFunctionsKt.m4649assert(this.dataMap == 0);
        CommonFunctionsKt.m4649assert(otherNode.nodeMap == 0);
        CommonFunctionsKt.m4649assert(otherNode.dataMap == 0);
        Object[] tempBuffer = Arrays.copyOf(this.buffer, this.buffer.length + otherNode.buffer.length);
        Intrinsics.checkNotNullExpressionValue(tempBuffer, "copyOf(...)");
        int i = this.buffer.length;
        IntProgression intProgressionStep = RangesKt.step(RangesKt.until(0, otherNode.buffer.length), 2);
        int j = intProgressionStep.getFirst();
        int last = intProgressionStep.getLast();
        int step = intProgressionStep.getStep();
        if ((step > 0 && j <= last) || (step < 0 && last <= j)) {
            while (true) {
                if (!collisionContainsKey(otherNode.buffer[j])) {
                    tempBuffer[i] = otherNode.buffer[j];
                    tempBuffer[i + 1] = otherNode.buffer[j + 1];
                    i += 2;
                } else {
                    intersectionCounter.setCount(intersectionCounter.getCount() + 1);
                }
                if (j == last) {
                    break;
                }
                j += step;
            }
        }
        int newSize = i;
        if (newSize == this.buffer.length) {
            return this;
        }
        if (newSize == otherNode.buffer.length) {
            return otherNode;
        }
        if (newSize == tempBuffer.length) {
            return new TrieNode<>(0, 0, tempBuffer, owner);
        }
        Object[] objArrCopyOf = Arrays.copyOf(tempBuffer, newSize);
        Intrinsics.checkNotNullExpressionValue(objArrCopyOf, "copyOf(...)");
        return new TrieNode<>(0, 0, objArrCopyOf, owner);
    }

    private final TrieNode<K, V> mutablePutAllFromOtherNodeCell(TrieNode<K, V> otherNode, int positionMask, int shift, DeltaCounter intersectionCounter, PersistentHashMapBuilder<K, V> mutator) {
        if (hasNodeAt(positionMask)) {
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex$runtime(positionMask));
            if (otherNode.hasNodeAt(positionMask)) {
                return trieNodeNodeAtIndex$runtime.mutablePutAll(otherNode.nodeAtIndex$runtime(otherNode.nodeIndex$runtime(positionMask)), shift + 5, intersectionCounter, mutator);
            }
            if (otherNode.hasEntryAt$runtime(positionMask)) {
                int keyIndex = otherNode.entryKeyIndex$runtime(positionMask);
                K kKeyAtIndex = otherNode.keyAtIndex(keyIndex);
                V vValueAtKeyIndex = otherNode.valueAtKeyIndex(keyIndex);
                int oldSize = mutator.size();
                TrieNode<K, V> trieNodeMutablePut = trieNodeNodeAtIndex$runtime.mutablePut(kKeyAtIndex != null ? kKeyAtIndex.hashCode() : 0, kKeyAtIndex, vValueAtKeyIndex, shift + 5, mutator);
                if (mutator.size() == oldSize) {
                    intersectionCounter.setCount(intersectionCounter.getCount() + 1);
                }
                return trieNodeMutablePut;
            }
            return trieNodeNodeAtIndex$runtime;
        }
        if (otherNode.hasNodeAt(positionMask)) {
            TrieNode<K, V> trieNodeNodeAtIndex$runtime2 = otherNode.nodeAtIndex$runtime(otherNode.nodeIndex$runtime(positionMask));
            if (hasEntryAt$runtime(positionMask)) {
                int keyIndex2 = entryKeyIndex$runtime(positionMask);
                K kKeyAtIndex2 = keyAtIndex(keyIndex2);
                if (!trieNodeNodeAtIndex$runtime2.containsKey(kKeyAtIndex2 != null ? kKeyAtIndex2.hashCode() : 0, kKeyAtIndex2, shift + 5)) {
                    return trieNodeNodeAtIndex$runtime2.mutablePut(kKeyAtIndex2 != null ? kKeyAtIndex2.hashCode() : 0, kKeyAtIndex2, valueAtKeyIndex(keyIndex2), shift + 5, mutator);
                }
                intersectionCounter.setCount(intersectionCounter.getCount() + 1);
            }
            return trieNodeNodeAtIndex$runtime2;
        }
        int thisKeyIndex = entryKeyIndex$runtime(positionMask);
        int iHashCode = 0;
        K kKeyAtIndex3 = keyAtIndex(thisKeyIndex);
        V vValueAtKeyIndex2 = valueAtKeyIndex(thisKeyIndex);
        int otherKeyIndex = otherNode.entryKeyIndex$runtime(positionMask);
        K kKeyAtIndex4 = otherNode.keyAtIndex(otherKeyIndex);
        V vValueAtKeyIndex3 = otherNode.valueAtKeyIndex(otherKeyIndex);
        int iHashCode2 = kKeyAtIndex3 != null ? kKeyAtIndex3.hashCode() : 0;
        if (kKeyAtIndex4 != null) {
            iHashCode = kKeyAtIndex4.hashCode();
        }
        return makeNode(iHashCode2, kKeyAtIndex3, vValueAtKeyIndex2, iHashCode, kKeyAtIndex4, vValueAtKeyIndex3, shift + 5, mutator.getOwnership());
    }

    private final int calculateSize() {
        if (this.nodeMap == 0) {
            return this.buffer.length / 2;
        }
        int numValues = Integer.bitCount(this.dataMap);
        int result = numValues;
        int length = this.buffer.length;
        for (int i = numValues * 2; i < length; i++) {
            result += nodeAtIndex$runtime(i).calculateSize();
        }
        return result;
    }

    private final boolean elementsIdentityEquals(TrieNode<K, V> otherNode) {
        if (this == otherNode) {
            return true;
        }
        if (this.nodeMap != otherNode.nodeMap || this.dataMap != otherNode.dataMap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != otherNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public final boolean containsKey(int keyHash, K key, int shift) {
        int keyPositionMask = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(keyPositionMask)) {
            return Intrinsics.areEqual(key, keyAtIndex(entryKeyIndex$runtime(keyPositionMask)));
        }
        if (hasNodeAt(keyPositionMask)) {
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex$runtime(keyPositionMask));
            if (shift == 30) {
                return trieNodeNodeAtIndex$runtime.collisionContainsKey(key);
            }
            return trieNodeNodeAtIndex$runtime.containsKey(keyHash, key, shift + 5);
        }
        return false;
    }

    public final V get(int keyHash, K key, int shift) {
        int keyPositionMask = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(keyPositionMask)) {
            int keyIndex = entryKeyIndex$runtime(keyPositionMask);
            if (Intrinsics.areEqual(key, keyAtIndex(keyIndex))) {
                return valueAtKeyIndex(keyIndex);
            }
            return null;
        }
        if (!hasNodeAt(keyPositionMask)) {
            return null;
        }
        TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex$runtime(keyPositionMask));
        if (shift == 30) {
            return trieNodeNodeAtIndex$runtime.collisionGet(key);
        }
        return trieNodeNodeAtIndex$runtime.get(keyHash, key, shift + 5);
    }

    public final TrieNode<K, V> mutablePutAll(TrieNode<K, V> otherNode, int shift, DeltaCounter intersectionCounter, PersistentHashMapBuilder<K, V> mutator) {
        TrieNode<K, V> trieNode;
        int $this$forEachOneBit$iv;
        boolean z;
        if (this == otherNode) {
            intersectionCounter.plusAssign(calculateSize());
            return this;
        }
        int i = shift;
        if (i > 30) {
            return mutableCollisionPutAll(otherNode, intersectionCounter, mutator.getOwnership());
        }
        int newNodeMap = this.nodeMap | otherNode.nodeMap;
        int newDataMap = (this.dataMap ^ otherNode.dataMap) & (~newNodeMap);
        int $this$forEachOneBit$iv2 = this.dataMap & otherNode.dataMap;
        int mask$iv = newNodeMap;
        int newNodeMap2 = $this$forEachOneBit$iv2;
        int newDataMap2 = newDataMap;
        int newDataMap3 = 0;
        while (newNodeMap2 != 0) {
            int bit$iv = Integer.lowestOneBit(newNodeMap2);
            Object leftKey = keyAtIndex(entryKeyIndex$runtime(bit$iv));
            Object rightKey = otherNode.keyAtIndex(otherNode.entryKeyIndex$runtime(bit$iv));
            if (!Intrinsics.areEqual(leftKey, rightKey)) {
                mask$iv |= bit$iv;
            } else {
                newDataMap2 |= bit$iv;
            }
            newDataMap3++;
            newNodeMap2 ^= bit$iv;
        }
        boolean z2 = true;
        boolean value$iv = (mask$iv & newDataMap2) == 0;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("Check failed.");
        }
        if (Intrinsics.areEqual(this.ownedBy, mutator.getOwnership()) && this.dataMap == newDataMap2 && this.nodeMap == mask$iv) {
            trieNode = this;
        } else {
            Object[] newBuffer = new Object[(Integer.bitCount(newDataMap2) * 2) + Integer.bitCount(mask$iv)];
            trieNode = new TrieNode<>(newDataMap2, mask$iv, newBuffer);
        }
        TrieNode<K, V> trieNode2 = trieNode;
        int $this$forEachOneBit$iv3 = mask$iv;
        int mask$iv2 = $this$forEachOneBit$iv3;
        int index$iv = 0;
        while (mask$iv2 != 0) {
            int bit$iv2 = Integer.lowestOneBit(mask$iv2);
            int index = index$iv;
            int newNodeIndex = (trieNode2.buffer.length - 1) - index;
            trieNode2.buffer[newNodeIndex] = mutablePutAllFromOtherNodeCell(otherNode, bit$iv2, i, intersectionCounter, mutator);
            index$iv++;
            mask$iv2 ^= bit$iv2;
            i = shift;
        }
        int $this$forEachOneBit$iv4 = newDataMap2;
        int mask$iv3 = $this$forEachOneBit$iv4;
        int index$iv2 = 0;
        while (mask$iv3 != 0) {
            int bit$iv3 = Integer.lowestOneBit(mask$iv3);
            int index2 = index$iv2;
            int newKeyIndex = index2 * 2;
            if (!otherNode.hasEntryAt$runtime(bit$iv3)) {
                z = z2;
                int oldKeyIndex = entryKeyIndex$runtime(bit$iv3);
                $this$forEachOneBit$iv = $this$forEachOneBit$iv4;
                trieNode2.buffer[newKeyIndex] = keyAtIndex(oldKeyIndex);
                trieNode2.buffer[newKeyIndex + 1] = valueAtKeyIndex(oldKeyIndex);
            } else {
                $this$forEachOneBit$iv = $this$forEachOneBit$iv4;
                z = z2;
                int oldKeyIndex2 = otherNode.entryKeyIndex$runtime(bit$iv3);
                trieNode2.buffer[newKeyIndex] = otherNode.keyAtIndex(oldKeyIndex2);
                trieNode2.buffer[newKeyIndex + 1] = otherNode.valueAtKeyIndex(oldKeyIndex2);
                if (hasEntryAt$runtime(bit$iv3)) {
                    intersectionCounter.setCount(intersectionCounter.getCount() + 1);
                }
            }
            index$iv2++;
            mask$iv3 ^= bit$iv3;
            z2 = z;
            $this$forEachOneBit$iv4 = $this$forEachOneBit$iv;
        }
        if (elementsIdentityEquals(trieNode2)) {
            return this;
        }
        return otherNode.elementsIdentityEquals(trieNode2) ? otherNode : trieNode2;
    }

    public final ModificationResult<K, V> put(int keyHash, K key, V value, int shift) {
        ModificationResult<K, V> modificationResultPut;
        int keyPositionMask = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(keyPositionMask)) {
            int keyIndex = entryKeyIndex$runtime(keyPositionMask);
            if (Intrinsics.areEqual(key, keyAtIndex(keyIndex))) {
                if (valueAtKeyIndex(keyIndex) == value) {
                    return null;
                }
                return updateValueAtIndex(keyIndex, value).asUpdateResult();
            }
            return moveEntryToNode(keyIndex, keyPositionMask, keyHash, key, value, shift).asInsertResult();
        }
        if (hasNodeAt(keyPositionMask)) {
            int nodeIndex = nodeIndex$runtime(keyPositionMask);
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex);
            if (shift != 30) {
                modificationResultPut = trieNodeNodeAtIndex$runtime.put(keyHash, key, value, shift + 5);
                if (modificationResultPut == null) {
                    return null;
                }
            } else {
                modificationResultPut = trieNodeNodeAtIndex$runtime.collisionPut(key, value);
                if (modificationResultPut == null) {
                    return null;
                }
            }
            ModificationResult<K, V> modificationResult = modificationResultPut;
            modificationResult.setNode(updateNodeAtIndex(nodeIndex, keyPositionMask, modificationResult.getNode()));
            return modificationResult;
        }
        return insertEntryAt(keyPositionMask, key, value).asInsertResult();
    }

    public final TrieNode<K, V> mutablePut(int keyHash, K key, V value, int shift, PersistentHashMapBuilder<K, V> mutator) {
        TrieNode<K, V> trieNodeMutablePut;
        PersistentHashMapBuilder<K, V> persistentHashMapBuilder;
        int keyPositionMask = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(keyPositionMask)) {
            int keyIndex = entryKeyIndex$runtime(keyPositionMask);
            if (Intrinsics.areEqual(key, keyAtIndex(keyIndex))) {
                mutator.setOperationResult$runtime(valueAtKeyIndex(keyIndex));
                if (valueAtKeyIndex(keyIndex) == value) {
                    return this;
                }
                return mutableUpdateValueAtIndex(keyIndex, value, mutator);
            }
            mutator.setSize(mutator.size() + 1);
            return mutableMoveEntryToNode(keyIndex, keyPositionMask, keyHash, key, value, shift, mutator.getOwnership());
        }
        if (hasNodeAt(keyPositionMask)) {
            int nodeIndex = nodeIndex$runtime(keyPositionMask);
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex);
            if (shift != 30) {
                trieNodeMutablePut = trieNodeNodeAtIndex$runtime.mutablePut(keyHash, key, value, shift + 5, mutator);
                persistentHashMapBuilder = mutator;
            } else {
                trieNodeMutablePut = trieNodeNodeAtIndex$runtime.mutableCollisionPut(key, value, mutator);
                persistentHashMapBuilder = mutator;
            }
            if (trieNodeNodeAtIndex$runtime == trieNodeMutablePut) {
                return this;
            }
            return mutableUpdateNodeAtIndex(nodeIndex, trieNodeMutablePut, persistentHashMapBuilder.getOwnership());
        }
        mutator.setSize(mutator.size() + 1);
        return mutableInsertEntryAt(keyPositionMask, key, value, mutator.getOwnership());
    }

    public final TrieNode<K, V> remove(int keyHash, K key, int shift) {
        TrieNode<K, V> trieNodeRemove;
        int keyPositionMask = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(keyPositionMask)) {
            int keyIndex = entryKeyIndex$runtime(keyPositionMask);
            if (Intrinsics.areEqual(key, keyAtIndex(keyIndex))) {
                return removeEntryAtIndex(keyIndex, keyPositionMask);
            }
            return this;
        }
        if (hasNodeAt(keyPositionMask)) {
            int nodeIndex = nodeIndex$runtime(keyPositionMask);
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex);
            if (shift == 30) {
                trieNodeRemove = trieNodeNodeAtIndex$runtime.collisionRemove(key);
            } else {
                trieNodeRemove = trieNodeNodeAtIndex$runtime.remove(keyHash, key, shift + 5);
            }
            return replaceNode(trieNodeNodeAtIndex$runtime, trieNodeRemove, nodeIndex, keyPositionMask);
        }
        return this;
    }

    private final TrieNode<K, V> replaceNode(TrieNode<K, V> targetNode, TrieNode<K, V> newNode, int nodeIndex, int positionMask) {
        if (newNode == null) {
            return removeNodeAtIndex(nodeIndex, positionMask);
        }
        if (targetNode != newNode) {
            return updateNodeAtIndex(nodeIndex, positionMask, newNode);
        }
        return this;
    }

    public final TrieNode<K, V> mutableRemove(int keyHash, K key, int shift, PersistentHashMapBuilder<K, V> mutator) {
        TrieNode<K, V> trieNodeMutableRemove;
        int keyPositionMask = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(keyPositionMask)) {
            int keyIndex = entryKeyIndex$runtime(keyPositionMask);
            if (Intrinsics.areEqual(key, keyAtIndex(keyIndex))) {
                return mutableRemoveEntryAtIndex(keyIndex, keyPositionMask, mutator);
            }
            return this;
        }
        if (hasNodeAt(keyPositionMask)) {
            int nodeIndex = nodeIndex$runtime(keyPositionMask);
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex);
            if (shift == 30) {
                trieNodeMutableRemove = trieNodeNodeAtIndex$runtime.mutableCollisionRemove(key, mutator);
            } else {
                trieNodeMutableRemove = trieNodeNodeAtIndex$runtime.mutableRemove(keyHash, key, shift + 5, mutator);
            }
            return mutableReplaceNode(trieNodeNodeAtIndex$runtime, trieNodeMutableRemove, nodeIndex, keyPositionMask, mutator.getOwnership());
        }
        return this;
    }

    private final TrieNode<K, V> mutableReplaceNode(TrieNode<K, V> targetNode, TrieNode<K, V> newNode, int nodeIndex, int positionMask, MutabilityOwnership owner) {
        if (newNode == null) {
            return mutableRemoveNodeAtIndex(nodeIndex, positionMask, owner);
        }
        if (this.ownedBy == owner || targetNode != newNode) {
            return mutableUpdateNodeAtIndex(nodeIndex, newNode, owner);
        }
        return this;
    }

    public final TrieNode<K, V> remove(int keyHash, K key, V value, int shift) {
        TrieNode<K, V> trieNodeRemove;
        int keyPositionMask = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(keyPositionMask)) {
            int keyIndex = entryKeyIndex$runtime(keyPositionMask);
            if (Intrinsics.areEqual(key, keyAtIndex(keyIndex)) && Intrinsics.areEqual(value, valueAtKeyIndex(keyIndex))) {
                return removeEntryAtIndex(keyIndex, keyPositionMask);
            }
            return this;
        }
        if (hasNodeAt(keyPositionMask)) {
            int nodeIndex = nodeIndex$runtime(keyPositionMask);
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex);
            if (shift == 30) {
                trieNodeRemove = trieNodeNodeAtIndex$runtime.collisionRemove(key, value);
            } else {
                trieNodeRemove = trieNodeNodeAtIndex$runtime.remove(keyHash, key, value, shift + 5);
            }
            return replaceNode(trieNodeNodeAtIndex$runtime, trieNodeRemove, nodeIndex, keyPositionMask);
        }
        return this;
    }

    public final TrieNode<K, V> mutableRemove(int keyHash, K key, V value, int shift, PersistentHashMapBuilder<K, V> mutator) {
        TrieNode<K, V> trieNodeMutableRemove;
        int keyPositionMask = 1 << TrieNodeKt.indexSegment(keyHash, shift);
        if (hasEntryAt$runtime(keyPositionMask)) {
            int keyIndex = entryKeyIndex$runtime(keyPositionMask);
            if (Intrinsics.areEqual(key, keyAtIndex(keyIndex)) && Intrinsics.areEqual(value, valueAtKeyIndex(keyIndex))) {
                return mutableRemoveEntryAtIndex(keyIndex, keyPositionMask, mutator);
            }
            return this;
        }
        if (hasNodeAt(keyPositionMask)) {
            int nodeIndex = nodeIndex$runtime(keyPositionMask);
            TrieNode<K, V> trieNodeNodeAtIndex$runtime = nodeAtIndex$runtime(nodeIndex);
            if (shift == 30) {
                trieNodeMutableRemove = trieNodeNodeAtIndex$runtime.mutableCollisionRemove(key, value, mutator);
            } else {
                trieNodeMutableRemove = trieNodeNodeAtIndex$runtime.mutableRemove(keyHash, key, value, shift + 5, mutator);
            }
            return mutableReplaceNode(trieNodeNodeAtIndex$runtime, trieNodeMutableRemove, nodeIndex, keyPositionMask, mutator.getOwnership());
        }
        return this;
    }

    public final void accept$runtime(Function5<? super TrieNode<K, V>, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> visitor) {
        accept(visitor, 0, 0);
    }

    private final void accept(Function5<? super TrieNode<K, V>, ? super Integer, ? super Integer, ? super Integer, ? super Integer, Unit> visitor, int hash, int shift) {
        visitor.invoke(this, Integer.valueOf(shift), Integer.valueOf(hash), Integer.valueOf(this.dataMap), Integer.valueOf(this.nodeMap));
        int nodePositions = this.nodeMap;
        while (nodePositions != 0) {
            int mask = Integer.lowestOneBit(nodePositions);
            int hashSegment = Integer.numberOfTrailingZeros(mask);
            nodeAtIndex$runtime(nodeIndex$runtime(mask)).accept(visitor, (hashSegment << shift) + hash, shift + 5);
            nodePositions -= mask;
        }
    }

    /* JADX INFO: compiled from: TrieNode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode$Companion;", "", "<init>", "()V", "EMPTY", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "", "getEMPTY$runtime", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableMap/TrieNode;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TrieNode getEMPTY$runtime() {
            return TrieNode.EMPTY;
        }
    }
}
