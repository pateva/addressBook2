import { AddressCreateBody } from "./AddressCreateBody"
import { ContactDetailsCreateBody } from "./ContactDetailsCreateBody"

export interface CreateRecordBody {
    userId: BigInteger | undefined,
    isPersonal: boolean,
    firstName: string,
    lastName: string,
    imageUrl: string,
    address: AddressCreateBody | null,
    contactDetails: ContactDetailsCreateBody[]
}