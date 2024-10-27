import { AddressCreateBody } from "./AddressCreateBody"
import { ContactDetailsCreateBody } from "./ContactDetailsCreateBody"

export interface CreateRecordBody {
    userId: BigInt,
    isPersonal: boolean,
    firstName: string,
    lastName: string,
    imageUrl: string,
    address: AddressCreateBody,
    contactDetails: ContactDetailsCreateBody[]
}