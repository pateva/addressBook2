import { ContactResponse } from "./ContactResponse"

export interface UserPartialResponse {
    id: BigInteger,
    email: string
    personalRecords: ContactResponse[] | []
    imageUrl: string
}