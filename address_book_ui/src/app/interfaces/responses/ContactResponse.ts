import { AddressResponse } from "./AddressResponse"
import { ContactDetailResponse } from "./ContactDetailResponse"

export interface ContactResponse {
    id: BigInteger,
    firstName: string,
    lastName: string,
    firmName: string,
    address: AddressResponse,
    personal: Boolean,
    imageUrl: string,
    contactDetails: ContactDetailResponse[]
}