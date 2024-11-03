import { ContactType } from "@app/shared/util/contactType";

export interface ContactDetailResponse {
    id: BigInteger,
    recordId: BigInteger,
    type: ContactType, 
    value: Number
}