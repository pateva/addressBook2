import { ContactResponse } from "./ContactResponse"

export interface UserResponse {
    id: BigInteger,
    email: string
    records: ContactResponse[]
}