package com.rdas.service;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import com.rdas.model.Employee;

import java.io.IOException;

/*
Every entity needs to have the serializer declared if it is to be inserted and selected from the cache.
There are same default serializers available inside the Hazelcast library.
It is based on StreamSerializer and ObjectDataInput.
 */
public class EmployeeSerializer implements StreamSerializer<Employee> {
    @Override
    public void write(ObjectDataOutput dataOutput, Employee employee) throws IOException {
        dataOutput.writeInt(employee.getId());
        dataOutput.writeInt(employee.getPersonId());
        dataOutput.writeUTF(employee.getCompany());
    }

    @Override
    public Employee read(ObjectDataInput objectDataInput) throws IOException {
        Employee e = Employee.builder().build();
        e.setId(objectDataInput.readInt());
        e.setPersonId(objectDataInput.readInt());
        e.setCompany(objectDataInput.readUTF());
        return e;
    }

    @Override
    public int getTypeId() {
        return 1;
    }

    @Override
    public void destroy() {

    }
}
