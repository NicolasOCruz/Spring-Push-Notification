package com.nicolascruz.push.api.v1.assembler;

import com.nicolascruz.push.api.v1.model.records.UserRecord;
import com.nicolascruz.push.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {

    private final ModelMapper modelMapper;

    public UserAssembler(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public User toDomainObject(UserRecord userRecord) {
        return modelMapper.map(userRecord, User.class);
    }

    public UserRecord toRecordObject(User user) {
        return modelMapper.map(user, UserRecord.class);
    }

    public void copyToDomainObject(UserRecord userRecord, User user) {
        modelMapper.map(userRecord, user);
    }

    public void copyToRecordObject(User user, UserRecord userRecord) {
        modelMapper.map(user, userRecord);
    }
}
