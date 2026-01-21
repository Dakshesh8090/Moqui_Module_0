if(!partyId){
    ec.message.addError("partyId is required")
    return
}
if(!lastName){
    ec.message.addError("lastName is required")
    return
}
if(!firstName){
    ec.message.addError("firstName is required")
    return
}

def party = ec.entity.find("party.Party")
        .condition("partyId", partyId)
        .one()

if(!party){
    ec.message.addError("Party with this `${partyId}` does not exist")
    return
}

def personValue = ec.entity.makeValue("party.Person")
personValue.partyId=partyId
personValue.firstName=firstName
personValue.lastName=lastName

if(dateOfBirth){
    personValue.dateOfBirth=dateOfBirth
}

personValue.create()

responseMessage="Person ${firstName} ${lastName} created successfully!"

