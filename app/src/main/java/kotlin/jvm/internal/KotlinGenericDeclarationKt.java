package kotlin.jvm.internal;

import java.io.IOException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: KotlinGenericDeclaration.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0004*\u00020\u0006H\u0082\u0080\u0004\u001a\u001e\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0082\u0080\u0004¨\u0006\r"}, d2 = {"findMethodBySignature", "Ljava/lang/reflect/GenericDeclaration;", "Lkotlin/reflect/KDeclarationContainer;", "signature", "", "computeMethodSignature", "Ljava/lang/reflect/Method;", "appendClass", "", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "start", "Ljava/lang/Class;", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class KotlinGenericDeclarationKt {
    /* JADX WARN: Removed duplicated region for block: B:14:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.reflect.GenericDeclaration findMethodBySignature(kotlin.reflect.KDeclarationContainer r10, java.lang.String r11) {
        /*
            java.lang.String r0 = "signature"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            boolean r0 = r10 instanceof kotlin.jvm.internal.ClassBasedDeclarationContainer
            r1 = 0
            if (r0 != 0) goto Lb
            return r1
        Lb:
            r0 = 40
            r2 = 2
            java.lang.String r0 = kotlin.text.StringsKt.substringBefore$default(r11, r0, r1, r2, r1)
            java.lang.String r2 = "<init>"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 != 0) goto L5b
            r2 = r10
            kotlin.jvm.internal.ClassBasedDeclarationContainer r2 = (kotlin.jvm.internal.ClassBasedDeclarationContainer) r2
            java.lang.Class r2 = r2.getJClass()
            java.lang.reflect.Method[] r2 = r2.getDeclaredMethods()
            java.lang.String r3 = "getDeclaredMethods(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            int r3 = r2.length
            r4 = 0
            r5 = r4
        L2f:
            if (r5 >= r3) goto L58
            r6 = r2[r5]
            r7 = r6
            java.lang.reflect.Method r7 = (java.lang.reflect.Method) r7
            r8 = 0
            java.lang.String r9 = r7.getName()
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r0)
            if (r9 == 0) goto L50
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.lang.String r9 = computeMethodSignature(r7)
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r11)
            if (r9 == 0) goto L50
            r9 = 1
            goto L51
        L50:
            r9 = r4
        L51:
            if (r9 == 0) goto L55
            r1 = r6
            goto L58
        L55:
            int r5 = r5 + 1
            goto L2f
        L58:
            java.lang.reflect.GenericDeclaration r1 = (java.lang.reflect.GenericDeclaration) r1
            return r1
        L5b:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Generic Java constructors are not supported: "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r10)
            r3 = 47
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r11)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.internal.KotlinGenericDeclarationKt.findMethodBySignature(kotlin.reflect.KDeclarationContainer, java.lang.String):java.lang.reflect.GenericDeclaration");
    }

    private static final String computeMethodSignature(Method $this$computeMethodSignature) throws IOException {
        StringBuilder $this$computeMethodSignature_u24lambda_u240 = new StringBuilder();
        $this$computeMethodSignature_u24lambda_u240.append($this$computeMethodSignature.getName());
        $this$computeMethodSignature_u24lambda_u240.append("(");
        Class<?>[] parameterTypes = $this$computeMethodSignature.getParameterTypes();
        Intrinsics.checkNotNullExpressionValue(parameterTypes, "getParameterTypes(...)");
        for (Class<?> cls : parameterTypes) {
            Intrinsics.checkNotNull(cls);
            appendClass($this$computeMethodSignature_u24lambda_u240, cls);
        }
        $this$computeMethodSignature_u24lambda_u240.append(")");
        Class<?> returnType = $this$computeMethodSignature.getReturnType();
        Intrinsics.checkNotNullExpressionValue(returnType, "getReturnType(...)");
        appendClass($this$computeMethodSignature_u24lambda_u240, returnType);
        return $this$computeMethodSignature_u24lambda_u240.toString();
    }

    private static final void appendClass(Appendable $this$appendClass, Class<?> cls) throws IOException {
        Class<?> cls2 = cls;
        while (cls2.isArray()) {
            $this$appendClass.append("[");
            Class<?> componentType = cls2.getComponentType();
            Intrinsics.checkNotNullExpressionValue(componentType, "getComponentType(...)");
            cls2 = componentType;
        }
        if (!Intrinsics.areEqual(cls2, Void.TYPE)) {
            if (!Intrinsics.areEqual(cls2, Integer.TYPE)) {
                if (!Intrinsics.areEqual(cls2, Long.TYPE)) {
                    if (!Intrinsics.areEqual(cls2, Short.TYPE)) {
                        if (!Intrinsics.areEqual(cls2, Byte.TYPE)) {
                            if (!Intrinsics.areEqual(cls2, Boolean.TYPE)) {
                                if (!Intrinsics.areEqual(cls2, Character.TYPE)) {
                                    if (!Intrinsics.areEqual(cls2, Float.TYPE)) {
                                        if (!Intrinsics.areEqual(cls2, Double.TYPE)) {
                                            $this$appendClass.append("L");
                                            String name = cls2.getName();
                                            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                                            $this$appendClass.append(StringsKt.replace$default(name, '.', '/', false, 4, (Object) null));
                                            $this$appendClass.append(";");
                                            return;
                                        }
                                        $this$appendClass.append("D");
                                        return;
                                    }
                                    $this$appendClass.append("F");
                                    return;
                                }
                                $this$appendClass.append("C");
                                return;
                            }
                            $this$appendClass.append("Z");
                            return;
                        }
                        $this$appendClass.append("B");
                        return;
                    }
                    $this$appendClass.append("S");
                    return;
                }
                $this$appendClass.append("J");
                return;
            }
            $this$appendClass.append("I");
            return;
        }
        $this$appendClass.append("V");
    }
}
