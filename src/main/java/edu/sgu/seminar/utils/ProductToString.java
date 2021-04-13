package edu.sgu.seminar.utils;

import com.emv.qrcode.core.model.mpm.TagLengthString;
import com.emv.qrcode.model.mpm.*;
import edu.sgu.seminar.entity.Invoice;

public class ProductToString {
   private final static MerchantAccountInformationTemplate merchanAccountInformationReservedAdditional = getMerchanAccountInformationReservedAdditional();
   private final static MerchantAccountInformationTemplate merchanAccountInformationReserved = getMerchanAccountInformationReserved();
   private final static MerchantInformationLanguageTemplate merchantInformationLanguage = getMerchantInformationLanguage();
   private final static UnreservedTemplate unreserved = getUnreserved();
   private final static TagLengthString rFUforEMVCo = new TagLengthString("65", "00");
    public static String productToStringEmv(Invoice invoice){
        MerchantPresentedMode merchantPresentMode= new MerchantPresentedMode();
        merchantPresentMode.setAdditionalDataField(getAddtionalDataField(invoice));
        merchantPresentMode.setMerchantCategoryCode("4111");
        merchantPresentMode.setMerchantCity("BEIJING");
        merchantPresentMode.setMerchantInformationLanguage(merchantInformationLanguage);
        merchantPresentMode.setMerchantName("BEST TRANSPORT");
        merchantPresentMode.setPayloadFormatIndicator("01");
        merchantPresentMode.setPointOfInitiationMethod("11");
        merchantPresentMode.setPostalCode("1234567");
        merchantPresentMode.setTipOrConvenienceIndicator("01");
        merchantPresentMode.setTransactionAmount("23.72");
        merchantPresentMode.setTransactionCurrency("156");
        merchantPresentMode.setValueOfConvenienceFeeFixed("500");
        merchantPresentMode.setValueOfConvenienceFeePercentage("5");
        merchantPresentMode.addMerchantAccountInformation(merchanAccountInformationReserved);
        merchantPresentMode.addMerchantAccountInformation(merchanAccountInformationReservedAdditional);
        merchantPresentMode.addRFUforEMVCo(rFUforEMVCo);
        merchantPresentMode.addUnreserved(unreserved);
        return merchantPresentMode.toString();
    }
    private static MerchantAccountInformationTemplate getMerchanAccountInformationReserved() {
        final MerchantAccountInformationReserved merchantAccountInformationValue = new MerchantAccountInformationReserved("0004");

        return new MerchantAccountInformationTemplate("02", merchantAccountInformationValue);
    }

    private static MerchantAccountInformationTemplate getMerchanAccountInformationReservedAdditional() {
        final TagLengthString paymentNetworkSpecific = new TagLengthString();
        paymentNetworkSpecific.setTag("01");
        paymentNetworkSpecific.setValue("abcd");

        final MerchantAccountInformationReservedAdditional merchantAccountInformationValue = new MerchantAccountInformationReservedAdditional();
        merchantAccountInformationValue.setGloballyUniqueIdentifier("hoge");
        merchantAccountInformationValue.addPaymentNetworkSpecific(paymentNetworkSpecific);

        return new MerchantAccountInformationTemplate("26", merchantAccountInformationValue);
    }

    private static UnreservedTemplate getUnreserved() {
        final TagLengthString contextSpecificData = new TagLengthString();
        contextSpecificData.setTag("07");
        contextSpecificData.setValue("12345678");

        final Unreserved value = new Unreserved();
        value.setGloballyUniqueIdentifier("A011223344998877");
        value.addContextSpecificData(contextSpecificData);

        final UnreservedTemplate unreserved = new UnreservedTemplate();
        unreserved.setValue(value);
        unreserved.setTag("80");

        return unreserved;
    }

    private static MerchantInformationLanguageTemplate getMerchantInformationLanguage() {

        final TagLengthString rFUforEMVCo = new TagLengthString();
        rFUforEMVCo.setTag("03");
        rFUforEMVCo.setValue("abcd");

        final MerchantInformationLanguage merchantInformationLanguageValue = new MerchantInformationLanguage();
        merchantInformationLanguageValue.setLanguagePreference("VI");
        merchantInformationLanguageValue.setMerchantName("HN");
        merchantInformationLanguageValue.setMerchantCity("HCMC");
        merchantInformationLanguageValue.addRFUforEMVCo(rFUforEMVCo);

        final MerchantInformationLanguageTemplate merchantInformationLanguage = new MerchantInformationLanguageTemplate();
        merchantInformationLanguage.setValue(merchantInformationLanguageValue);

        return merchantInformationLanguage;
    }

    private static AdditionalDataFieldTemplate getAddtionalDataField(Invoice invoice) {
        final PaymentSystemSpecific paymentSystemSpecific = new PaymentSystemSpecific();
        paymentSystemSpecific.setGloballyUniqueIdentifier("1");
        paymentSystemSpecific.addPaymentSystemSpecific(new TagLengthString("01", "i"));

        final PaymentSystemSpecificTemplate paymentSystemSpecificTemplate = new PaymentSystemSpecificTemplate();
        paymentSystemSpecificTemplate.setTag("50");
        paymentSystemSpecificTemplate.setValue(paymentSystemSpecific);

        final AdditionalDataField additionalDataFieldValue = new AdditionalDataField();
        additionalDataFieldValue.setAdditionalConsumerDataRequest("tuvxy");
        additionalDataFieldValue.setBillNumber(invoice.getId());
        additionalDataFieldValue.setCustomerLabel(invoice.getUser().getFullName());
        additionalDataFieldValue.setLoyaltyNumber("54321");
        additionalDataFieldValue.setMobileNumber(invoice.getPhoneNumber());
        additionalDataFieldValue.setPurposeTransaction("pqres");
        additionalDataFieldValue.setReferenceLabel("abcde");
        additionalDataFieldValue.setStoreLabel("09876");
        additionalDataFieldValue.setTerminalLabel("klmno");
        additionalDataFieldValue.addPaymentSystemSpecific(paymentSystemSpecificTemplate);
        final AdditionalDataFieldTemplate additionalDataField = new AdditionalDataFieldTemplate();
        additionalDataField.setValue(additionalDataFieldValue);

        return additionalDataField;
    }
}
