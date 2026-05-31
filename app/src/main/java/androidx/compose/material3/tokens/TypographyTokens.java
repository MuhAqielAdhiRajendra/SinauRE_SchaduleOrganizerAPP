package androidx.compose.material3.tokens;

import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.GenericFontFamily;
import kotlin.Metadata;

/* JADX INFO: compiled from: TypographyTokens.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b=\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0007R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0007R\u0011\u0010\u0014\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007R\u0011\u0010\u0016\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0007R\u0011\u0010\u0018\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u0011\u0010\u001a\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007R\u0011\u0010\u001c\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0007R\u0011\u0010\u001e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0007R\u0011\u0010 \u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0007R\u0011\u0010\"\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0007R\u0011\u0010$\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0007R\u0011\u0010&\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0007R\u0011\u0010(\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0007R\u0011\u0010*\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0007R\u0011\u0010,\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\u0007R\u0011\u0010.\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0007R\u0011\u00100\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u0007R\u0011\u00102\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0007R\u0011\u00104\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0007R\u0011\u00106\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b7\u0010\u0007R\u0011\u00108\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\u0007R\u0011\u0010:\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b;\u0010\u0007R\u0011\u0010<\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b=\u0010\u0007R\u0011\u0010>\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u0007R\u0011\u0010@\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bA\u0010\u0007¨\u0006B"}, d2 = {"Landroidx/compose/material3/tokens/TypographyTokens;", "", "<init>", "()V", "BodyLarge", "Landroidx/compose/ui/text/TextStyle;", "getBodyLarge", "()Landroidx/compose/ui/text/TextStyle;", "BodyMedium", "getBodyMedium", "BodySmall", "getBodySmall", "DisplayLarge", "getDisplayLarge", "DisplayMedium", "getDisplayMedium", "DisplaySmall", "getDisplaySmall", "HeadlineLarge", "getHeadlineLarge", "HeadlineMedium", "getHeadlineMedium", "HeadlineSmall", "getHeadlineSmall", "LabelLarge", "getLabelLarge", "LabelMedium", "getLabelMedium", "LabelSmall", "getLabelSmall", "TitleLarge", "getTitleLarge", "TitleMedium", "getTitleMedium", "TitleSmall", "getTitleSmall", "BodyLargeEmphasized", "getBodyLargeEmphasized", "BodyMediumEmphasized", "getBodyMediumEmphasized", "BodySmallEmphasized", "getBodySmallEmphasized", "DisplayLargeEmphasized", "getDisplayLargeEmphasized", "DisplayMediumEmphasized", "getDisplayMediumEmphasized", "DisplaySmallEmphasized", "getDisplaySmallEmphasized", "HeadlineLargeEmphasized", "getHeadlineLargeEmphasized", "HeadlineMediumEmphasized", "getHeadlineMediumEmphasized", "HeadlineSmallEmphasized", "getHeadlineSmallEmphasized", "LabelLargeEmphasized", "getLabelLargeEmphasized", "LabelMediumEmphasized", "getLabelMediumEmphasized", "LabelSmallEmphasized", "getLabelSmallEmphasized", "TitleLargeEmphasized", "getTitleLargeEmphasized", "TitleMediumEmphasized", "getTitleMediumEmphasized", "TitleSmallEmphasized", "getTitleSmallEmphasized", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TypographyTokens {
    public static final int $stable = 0;
    private static final TextStyle BodyLarge;
    private static final TextStyle BodyLargeEmphasized;
    private static final TextStyle BodyMedium;
    private static final TextStyle BodyMediumEmphasized;
    private static final TextStyle BodySmall;
    private static final TextStyle BodySmallEmphasized;
    private static final TextStyle DisplayLarge;
    private static final TextStyle DisplayLargeEmphasized;
    private static final TextStyle DisplayMedium;
    private static final TextStyle DisplayMediumEmphasized;
    private static final TextStyle DisplaySmall;
    private static final TextStyle DisplaySmallEmphasized;
    private static final TextStyle HeadlineLarge;
    private static final TextStyle HeadlineLargeEmphasized;
    private static final TextStyle HeadlineMedium;
    private static final TextStyle HeadlineMediumEmphasized;
    private static final TextStyle HeadlineSmall;
    private static final TextStyle HeadlineSmallEmphasized;
    public static final TypographyTokens INSTANCE = new TypographyTokens();
    private static final TextStyle LabelLarge;
    private static final TextStyle LabelLargeEmphasized;
    private static final TextStyle LabelMedium;
    private static final TextStyle LabelMediumEmphasized;
    private static final TextStyle LabelSmall;
    private static final TextStyle LabelSmallEmphasized;
    private static final TextStyle TitleLarge;
    private static final TextStyle TitleLargeEmphasized;
    private static final TextStyle TitleMedium;
    private static final TextStyle TitleMediumEmphasized;
    private static final TextStyle TitleSmall;
    private static final TextStyle TitleSmallEmphasized;

    private TypographyTokens() {
    }

    public final TextStyle getBodyLarge() {
        return BodyLarge;
    }

    static {
        TextStyle defaultTextStyle = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyLargeFont = TypeScaleTokens.INSTANCE.getBodyLargeFont();
        BodyLarge = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle, 0L, TypeScaleTokens.INSTANCE.m4279getBodyLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodyLargeWeight(), null, null, bodyLargeFont, null, TypeScaleTokens.INSTANCE.m4280getBodyLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4278getBodyLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle2 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyMediumFont = TypeScaleTokens.INSTANCE.getBodyMediumFont();
        BodyMedium = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle2, 0L, TypeScaleTokens.INSTANCE.m4285getBodyMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodyMediumWeight(), null, null, bodyMediumFont, null, TypeScaleTokens.INSTANCE.m4286getBodyMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4284getBodyMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle3 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodySmallFont = TypeScaleTokens.INSTANCE.getBodySmallFont();
        BodySmall = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle3, 0L, TypeScaleTokens.INSTANCE.m4291getBodySmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodySmallWeight(), null, null, bodySmallFont, null, TypeScaleTokens.INSTANCE.m4292getBodySmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4290getBodySmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle4 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayLargeFont = TypeScaleTokens.INSTANCE.getDisplayLargeFont();
        DisplayLarge = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle4, 0L, TypeScaleTokens.INSTANCE.m4297getDisplayLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplayLargeWeight(), null, null, displayLargeFont, null, TypeScaleTokens.INSTANCE.m4298getDisplayLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4296getDisplayLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle5 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayMediumFont = TypeScaleTokens.INSTANCE.getDisplayMediumFont();
        DisplayMedium = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle5, 0L, TypeScaleTokens.INSTANCE.m4303getDisplayMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplayMediumWeight(), null, null, displayMediumFont, null, TypeScaleTokens.INSTANCE.m4304getDisplayMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4302getDisplayMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle6 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displaySmallFont = TypeScaleTokens.INSTANCE.getDisplaySmallFont();
        DisplaySmall = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle6, 0L, TypeScaleTokens.INSTANCE.m4309getDisplaySmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplaySmallWeight(), null, null, displaySmallFont, null, TypeScaleTokens.INSTANCE.m4310getDisplaySmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4308getDisplaySmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle7 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineLargeFont = TypeScaleTokens.INSTANCE.getHeadlineLargeFont();
        HeadlineLarge = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle7, 0L, TypeScaleTokens.INSTANCE.m4315getHeadlineLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineLargeWeight(), null, null, headlineLargeFont, null, TypeScaleTokens.INSTANCE.m4316getHeadlineLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4314getHeadlineLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle8 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineMediumFont = TypeScaleTokens.INSTANCE.getHeadlineMediumFont();
        HeadlineMedium = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle8, 0L, TypeScaleTokens.INSTANCE.m4321getHeadlineMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineMediumWeight(), null, null, headlineMediumFont, null, TypeScaleTokens.INSTANCE.m4322getHeadlineMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4320getHeadlineMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle9 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineSmallFont = TypeScaleTokens.INSTANCE.getHeadlineSmallFont();
        HeadlineSmall = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle9, 0L, TypeScaleTokens.INSTANCE.m4327getHeadlineSmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineSmallWeight(), null, null, headlineSmallFont, null, TypeScaleTokens.INSTANCE.m4328getHeadlineSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4326getHeadlineSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle10 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelLargeFont = TypeScaleTokens.INSTANCE.getLabelLargeFont();
        LabelLarge = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle10, 0L, TypeScaleTokens.INSTANCE.m4333getLabelLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelLargeWeight(), null, null, labelLargeFont, null, TypeScaleTokens.INSTANCE.m4334getLabelLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4332getLabelLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle11 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelMediumFont = TypeScaleTokens.INSTANCE.getLabelMediumFont();
        LabelMedium = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle11, 0L, TypeScaleTokens.INSTANCE.m4339getLabelMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelMediumWeight(), null, null, labelMediumFont, null, TypeScaleTokens.INSTANCE.m4340getLabelMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4338getLabelMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle12 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelSmallFont = TypeScaleTokens.INSTANCE.getLabelSmallFont();
        LabelSmall = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle12, 0L, TypeScaleTokens.INSTANCE.m4345getLabelSmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelSmallWeight(), null, null, labelSmallFont, null, TypeScaleTokens.INSTANCE.m4346getLabelSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4344getLabelSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle13 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleLargeFont = TypeScaleTokens.INSTANCE.getTitleLargeFont();
        TitleLarge = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle13, 0L, TypeScaleTokens.INSTANCE.m4351getTitleLargeSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleLargeWeight(), null, null, titleLargeFont, null, TypeScaleTokens.INSTANCE.m4352getTitleLargeTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4350getTitleLargeLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle14 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleMediumFont = TypeScaleTokens.INSTANCE.getTitleMediumFont();
        TitleMedium = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle14, 0L, TypeScaleTokens.INSTANCE.m4357getTitleMediumSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleMediumWeight(), null, null, titleMediumFont, null, TypeScaleTokens.INSTANCE.m4358getTitleMediumTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4356getTitleMediumLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle15 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleSmallFont = TypeScaleTokens.INSTANCE.getTitleSmallFont();
        TitleSmall = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle15, 0L, TypeScaleTokens.INSTANCE.m4363getTitleSmallSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleSmallWeight(), null, null, titleSmallFont, null, TypeScaleTokens.INSTANCE.m4364getTitleSmallTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4362getTitleSmallLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle16 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyLargeEmphasizedFont = TypeScaleTokens.INSTANCE.getBodyLargeEmphasizedFont();
        BodyLargeEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle16, 0L, TypeScaleTokens.INSTANCE.m4276getBodyLargeEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodyLargeEmphasizedWeight(), null, null, bodyLargeEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4277getBodyLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4275getBodyLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle17 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodyMediumEmphasizedFont = TypeScaleTokens.INSTANCE.getBodyMediumEmphasizedFont();
        BodyMediumEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle17, 0L, TypeScaleTokens.INSTANCE.m4282getBodyMediumEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodyMediumEmphasizedWeight(), null, null, bodyMediumEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4283getBodyMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4281getBodyMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle18 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily bodySmallEmphasizedFont = TypeScaleTokens.INSTANCE.getBodySmallEmphasizedFont();
        BodySmallEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle18, 0L, TypeScaleTokens.INSTANCE.m4288getBodySmallEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getBodySmallEmphasizedWeight(), null, null, bodySmallEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4289getBodySmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4287getBodySmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle19 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayLargeEmphasizedFont = TypeScaleTokens.INSTANCE.getDisplayLargeEmphasizedFont();
        DisplayLargeEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle19, 0L, TypeScaleTokens.INSTANCE.m4294getDisplayLargeEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplayLargeEmphasizedWeight(), null, null, displayLargeEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4295getDisplayLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4293getDisplayLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle20 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displayMediumEmphasizedFont = TypeScaleTokens.INSTANCE.getDisplayMediumEmphasizedFont();
        DisplayMediumEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle20, 0L, TypeScaleTokens.INSTANCE.m4300getDisplayMediumEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplayMediumEmphasizedWeight(), null, null, displayMediumEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4301getDisplayMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4299getDisplayMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle21 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily displaySmallEmphasizedFont = TypeScaleTokens.INSTANCE.getDisplaySmallEmphasizedFont();
        DisplaySmallEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle21, 0L, TypeScaleTokens.INSTANCE.m4306getDisplaySmallEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getDisplaySmallEmphasizedWeight(), null, null, displaySmallEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4307getDisplaySmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4305getDisplaySmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle22 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineLargeEmphasizedFont = TypeScaleTokens.INSTANCE.getHeadlineLargeEmphasizedFont();
        HeadlineLargeEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle22, 0L, TypeScaleTokens.INSTANCE.m4312getHeadlineLargeEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineLargeEmphasizedWeight(), null, null, headlineLargeEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4313getHeadlineLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4311getHeadlineLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle23 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineMediumEmphasizedFont = TypeScaleTokens.INSTANCE.getHeadlineMediumEmphasizedFont();
        HeadlineMediumEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle23, 0L, TypeScaleTokens.INSTANCE.m4318getHeadlineMediumEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineMediumEmphasizedWeight(), null, null, headlineMediumEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4319getHeadlineMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4317getHeadlineMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle24 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily headlineSmallEmphasizedFont = TypeScaleTokens.INSTANCE.getHeadlineSmallEmphasizedFont();
        HeadlineSmallEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle24, 0L, TypeScaleTokens.INSTANCE.m4324getHeadlineSmallEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getHeadlineSmallEmphasizedWeight(), null, null, headlineSmallEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4325getHeadlineSmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4323getHeadlineSmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle25 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelLargeEmphasizedFont = TypeScaleTokens.INSTANCE.getLabelLargeEmphasizedFont();
        LabelLargeEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle25, 0L, TypeScaleTokens.INSTANCE.m4330getLabelLargeEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelLargeEmphasizedWeight(), null, null, labelLargeEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4331getLabelLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4329getLabelLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle26 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelMediumEmphasizedFont = TypeScaleTokens.INSTANCE.getLabelMediumEmphasizedFont();
        LabelMediumEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle26, 0L, TypeScaleTokens.INSTANCE.m4336getLabelMediumEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelMediumEmphasizedWeight(), null, null, labelMediumEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4337getLabelMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4335getLabelMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle27 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily labelSmallEmphasizedFont = TypeScaleTokens.INSTANCE.getLabelSmallEmphasizedFont();
        LabelSmallEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle27, 0L, TypeScaleTokens.INSTANCE.m4342getLabelSmallEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getLabelSmallEmphasizedWeight(), null, null, labelSmallEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4343getLabelSmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4341getLabelSmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle28 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleLargeEmphasizedFont = TypeScaleTokens.INSTANCE.getTitleLargeEmphasizedFont();
        TitleLargeEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle28, 0L, TypeScaleTokens.INSTANCE.m4348getTitleLargeEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleLargeEmphasizedWeight(), null, null, titleLargeEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4349getTitleLargeEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4347getTitleLargeEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle29 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleMediumEmphasizedFont = TypeScaleTokens.INSTANCE.getTitleMediumEmphasizedFont();
        TitleMediumEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle29, 0L, TypeScaleTokens.INSTANCE.m4354getTitleMediumEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleMediumEmphasizedWeight(), null, null, titleMediumEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4355getTitleMediumEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4353getTitleMediumEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
        TextStyle defaultTextStyle30 = TypographyTokensKt.getDefaultTextStyle();
        GenericFontFamily titleSmallEmphasizedFont = TypeScaleTokens.INSTANCE.getTitleSmallEmphasizedFont();
        TitleSmallEmphasized = TextStyle.m7586copyp1EtxEg$default(defaultTextStyle30, 0L, TypeScaleTokens.INSTANCE.m4360getTitleSmallEmphasizedSizeXSAIIZE(), TypeScaleTokens.INSTANCE.getTitleSmallEmphasizedWeight(), null, null, titleSmallEmphasizedFont, null, TypeScaleTokens.INSTANCE.m4361getTitleSmallEmphasizedTrackingXSAIIZE(), null, null, null, 0L, null, null, null, 0, 0, TypeScaleTokens.INSTANCE.m4359getTitleSmallEmphasizedLineHeightXSAIIZE(), null, null, null, 0, 0, null, 16645977, null);
    }

    public final TextStyle getBodyMedium() {
        return BodyMedium;
    }

    public final TextStyle getBodySmall() {
        return BodySmall;
    }

    public final TextStyle getDisplayLarge() {
        return DisplayLarge;
    }

    public final TextStyle getDisplayMedium() {
        return DisplayMedium;
    }

    public final TextStyle getDisplaySmall() {
        return DisplaySmall;
    }

    public final TextStyle getHeadlineLarge() {
        return HeadlineLarge;
    }

    public final TextStyle getHeadlineMedium() {
        return HeadlineMedium;
    }

    public final TextStyle getHeadlineSmall() {
        return HeadlineSmall;
    }

    public final TextStyle getLabelLarge() {
        return LabelLarge;
    }

    public final TextStyle getLabelMedium() {
        return LabelMedium;
    }

    public final TextStyle getLabelSmall() {
        return LabelSmall;
    }

    public final TextStyle getTitleLarge() {
        return TitleLarge;
    }

    public final TextStyle getTitleMedium() {
        return TitleMedium;
    }

    public final TextStyle getTitleSmall() {
        return TitleSmall;
    }

    public final TextStyle getBodyLargeEmphasized() {
        return BodyLargeEmphasized;
    }

    public final TextStyle getBodyMediumEmphasized() {
        return BodyMediumEmphasized;
    }

    public final TextStyle getBodySmallEmphasized() {
        return BodySmallEmphasized;
    }

    public final TextStyle getDisplayLargeEmphasized() {
        return DisplayLargeEmphasized;
    }

    public final TextStyle getDisplayMediumEmphasized() {
        return DisplayMediumEmphasized;
    }

    public final TextStyle getDisplaySmallEmphasized() {
        return DisplaySmallEmphasized;
    }

    public final TextStyle getHeadlineLargeEmphasized() {
        return HeadlineLargeEmphasized;
    }

    public final TextStyle getHeadlineMediumEmphasized() {
        return HeadlineMediumEmphasized;
    }

    public final TextStyle getHeadlineSmallEmphasized() {
        return HeadlineSmallEmphasized;
    }

    public final TextStyle getLabelLargeEmphasized() {
        return LabelLargeEmphasized;
    }

    public final TextStyle getLabelMediumEmphasized() {
        return LabelMediumEmphasized;
    }

    public final TextStyle getLabelSmallEmphasized() {
        return LabelSmallEmphasized;
    }

    public final TextStyle getTitleLargeEmphasized() {
        return TitleLargeEmphasized;
    }

    public final TextStyle getTitleMediumEmphasized() {
        return TitleMediumEmphasized;
    }

    public final TextStyle getTitleSmallEmphasized() {
        return TitleSmallEmphasized;
    }
}
