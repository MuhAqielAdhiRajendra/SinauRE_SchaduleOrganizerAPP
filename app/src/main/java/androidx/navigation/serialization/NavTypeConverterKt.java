package androidx.navigation.serialization;

import androidx.navigation.NavType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.text.StringsKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.internal.CollectionDescriptorsKt;

/* JADX INFO: compiled from: NavTypeConverter.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\t"}, d2 = {"getNavType", "Landroidx/navigation/NavType;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "toInternalType", "Landroidx/navigation/serialization/InternalType;", "matchKType", "", "kType", "Lkotlin/reflect/KType;", "navigation-common_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class NavTypeConverterKt {

    /* JADX INFO: compiled from: NavTypeConverter.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InternalType.values().length];
            try {
                iArr[InternalType.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[InternalType.STRING_NULLABLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[InternalType.INT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[InternalType.BOOL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[InternalType.DOUBLE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[InternalType.FLOAT.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[InternalType.LONG.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[InternalType.ENUM.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[InternalType.INT_NULLABLE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[InternalType.BOOL_NULLABLE.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[InternalType.DOUBLE_NULLABLE.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr[InternalType.FLOAT_NULLABLE.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr[InternalType.LONG_NULLABLE.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr[InternalType.INT_ARRAY.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr[InternalType.BOOL_ARRAY.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr[InternalType.DOUBLE_ARRAY.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                iArr[InternalType.FLOAT_ARRAY.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                iArr[InternalType.LONG_ARRAY.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                iArr[InternalType.ARRAY.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                iArr[InternalType.LIST.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                iArr[InternalType.ENUM_NULLABLE.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final NavType<?> getNavType(SerialDescriptor $this$getNavType) {
        Intrinsics.checkNotNullParameter($this$getNavType, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[toInternalType($this$getNavType).ordinal()]) {
            case 1:
                return InternalNavType.INSTANCE.getStringNonNullableType();
            case 2:
                return NavType.StringType;
            case 3:
                return NavType.IntType;
            case 4:
                return NavType.BoolType;
            case 5:
                return InternalNavType.INSTANCE.getDoubleType();
            case 6:
                return NavType.FloatType;
            case 7:
                return NavType.LongType;
            case 8:
                return NavTypeConverter_androidKt.parseEnum($this$getNavType);
            case 9:
                return InternalNavType.INSTANCE.getIntNullableType();
            case 10:
                return InternalNavType.INSTANCE.getBoolNullableType();
            case 11:
                return InternalNavType.INSTANCE.getDoubleNullableType();
            case 12:
                return InternalNavType.INSTANCE.getFloatNullableType();
            case 13:
                return InternalNavType.INSTANCE.getLongNullableType();
            case 14:
                NavType type = NavType.IntArrayType;
                return type;
            case 15:
                NavType type2 = NavType.BoolArrayType;
                return type2;
            case 16:
                NavType type3 = InternalNavType.INSTANCE.getDoubleArrayType();
                return type3;
            case 17:
                NavType type4 = NavType.FloatArrayType;
                return type4;
            case 18:
                NavType type5 = NavType.LongArrayType;
                return type5;
            case 19:
                InternalType typeParameter = toInternalType($this$getNavType.getElementDescriptor(0));
                switch (WhenMappings.$EnumSwitchMapping$0[typeParameter.ordinal()]) {
                    case 1:
                        NavType type6 = NavType.StringArrayType;
                        return type6;
                    case 2:
                        NavType type7 = InternalNavType.INSTANCE.getStringNullableArrayType();
                        return type7;
                    default:
                        NavType type8 = UNKNOWN.INSTANCE;
                        return type8;
                }
            case 20:
                InternalType typeParameter2 = toInternalType($this$getNavType.getElementDescriptor(0));
                switch (WhenMappings.$EnumSwitchMapping$0[typeParameter2.ordinal()]) {
                    case 1:
                        return NavType.StringListType;
                    case 2:
                        return InternalNavType.INSTANCE.getStringNullableListType();
                    case 3:
                        return NavType.IntListType;
                    case 4:
                        return NavType.BoolListType;
                    case 5:
                        return InternalNavType.INSTANCE.getDoubleListType();
                    case 6:
                        return NavType.FloatListType;
                    case 7:
                        return NavType.LongListType;
                    case 8:
                        return NavTypeConverter_androidKt.parseEnumList($this$getNavType);
                    default:
                        NavType type9 = UNKNOWN.INSTANCE;
                        return type9;
                }
            case 21:
                return NavTypeConverter_androidKt.parseNullableEnum($this$getNavType);
            default:
                NavType type10 = UNKNOWN.INSTANCE;
                return type10;
        }
    }

    private static final InternalType toInternalType(SerialDescriptor $this$toInternalType) {
        String serialName = StringsKt.replace$default($this$toInternalType.getSerialName(), "?", "", false, 4, (Object) null);
        return Intrinsics.areEqual($this$toInternalType.getKind(), SerialKind.ENUM.INSTANCE) ? $this$toInternalType.isNullable() ? InternalType.ENUM_NULLABLE : InternalType.ENUM : Intrinsics.areEqual(serialName, "kotlin.Int") ? $this$toInternalType.isNullable() ? InternalType.INT_NULLABLE : InternalType.INT : Intrinsics.areEqual(serialName, "kotlin.Boolean") ? $this$toInternalType.isNullable() ? InternalType.BOOL_NULLABLE : InternalType.BOOL : Intrinsics.areEqual(serialName, "kotlin.Double") ? $this$toInternalType.isNullable() ? InternalType.DOUBLE_NULLABLE : InternalType.DOUBLE : Intrinsics.areEqual(serialName, "kotlin.Float") ? $this$toInternalType.isNullable() ? InternalType.FLOAT_NULLABLE : InternalType.FLOAT : Intrinsics.areEqual(serialName, "kotlin.Long") ? $this$toInternalType.isNullable() ? InternalType.LONG_NULLABLE : InternalType.LONG : Intrinsics.areEqual(serialName, "kotlin.String") ? $this$toInternalType.isNullable() ? InternalType.STRING_NULLABLE : InternalType.STRING : Intrinsics.areEqual(serialName, "kotlin.IntArray") ? InternalType.INT_ARRAY : Intrinsics.areEqual(serialName, "kotlin.DoubleArray") ? InternalType.DOUBLE_ARRAY : Intrinsics.areEqual(serialName, "kotlin.BooleanArray") ? InternalType.BOOL_ARRAY : Intrinsics.areEqual(serialName, "kotlin.FloatArray") ? InternalType.FLOAT_ARRAY : Intrinsics.areEqual(serialName, "kotlin.LongArray") ? InternalType.LONG_ARRAY : Intrinsics.areEqual(serialName, CollectionDescriptorsKt.ARRAY_NAME) ? InternalType.ARRAY : StringsKt.startsWith$default(serialName, CollectionDescriptorsKt.ARRAY_LIST_NAME, false, 2, (Object) null) ? InternalType.LIST : InternalType.UNKNOWN;
    }

    public static final boolean matchKType(SerialDescriptor $this$matchKType, KType kType) {
        Intrinsics.checkNotNullParameter($this$matchKType, "<this>");
        Intrinsics.checkNotNullParameter(kType, "kType");
        if ($this$matchKType.isNullable() != kType.isMarkedNullable()) {
            return false;
        }
        KSerializer<Object> kSerializerSerializerOrNull = SerializersKt.serializerOrNull(kType);
        if (kSerializerSerializerOrNull == null) {
            throw new IllegalStateException(("Cannot find KSerializer for [" + $this$matchKType.getSerialName() + "]. If applicable, custom KSerializers for custom and third-party KType is currently not supported when declared directly on a class field via @Serializable(with = ...). Please use @Serializable or @Serializable(with = ...) on the class or object declaration.").toString());
        }
        return Intrinsics.areEqual($this$matchKType, kSerializerSerializerOrNull.getDescriptor());
    }
}
