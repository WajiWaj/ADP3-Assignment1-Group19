package za.ac.cput.repository;

import za.ac.cput.domain.Service;

import java.util.Set;

public class ServiceRepository implements IServiceRepository{

    private static ServiceRepository repository = null;
    private Set<Service> serviceDB = null;

    static ServiceRepository getRepository() {

        if (repository == null) {
            repository = new ServiceRepository();
        }
        return repository;
    }

    @Override
    public Service create(Service service) {
        boolean created = serviceDB.add(service);
        if (!created) return null;
        return service;
    }

    @Override
    public Service read(String serviceId) {
        Service service = serviceDB.stream()
                .filter(e -> e.getServiceId().equals(serviceId))
                .findAny()
                .orElse(null);
        return service;
    }

    @Override
    public Service update(Service service) {
        Service oldService = read(service.getServiceId());
        if (oldService != null) {
            serviceDB.remove(oldService);
            serviceDB.add(service);
            return service;
        }
        return null;
    }

    @Override
    public boolean delete(String serviceId) {
        Service deleteService = read(serviceId);
        if (deleteService == null)
            return false;
        serviceDB.remove(deleteService);
        return true;
    }

    @Override
    public Set<Service> getAll() {
        return serviceDB;
    }
}
