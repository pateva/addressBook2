import { AddressResponse } from "./AddressResponse";
import { ContactDetailResponse } from "./ContactDetailResponse";

export interface RecordResponse {
    id: BigInt,
    firstName: string,
    lastName: string,
    firmName: string,
    address: AddressResponse,
    contactDetails: ContactDetailResponse,
    //TODO update
    notes: string,
    imageUrl: string,
    isPersonal: boolean
}