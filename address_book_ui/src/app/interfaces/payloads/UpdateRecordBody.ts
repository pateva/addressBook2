import { AddressCreateBody } from "./AddressCreateBody"
import { ContactDetailsCreateBody } from "./ContactDetailsCreateBody"

export interface UpdateRecordBody {
    id: BigInteger,
    userId: BigInteger | undefined,
    isPersonal: Boolean,
    firstName: string,
    lastName: string,
    imageUrl: string,
    address: AddressCreateBody | null,
    contactDetails: ContactDetailsCreateBody[]
}