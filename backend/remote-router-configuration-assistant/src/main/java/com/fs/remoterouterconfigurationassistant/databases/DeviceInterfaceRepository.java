package com.fs.remoterouterconfigurationassistant.databases;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.fs.remoterouterconfigurationassistant.databases.entities.DeviceInterfaceDao;
import com.fs.remoterouterconfigurationassistant.databases.entities.NetworkDeviceDao;
@Repository
public interface DeviceInterfaceRepository extends JpaRepository<DeviceInterfaceDao,Long> {
    @Query("SELECT COUNT(d) FROM DeviceInterfaceDao d WHERE d.networkDeviceDao.id = :deviceId AND d.isConnected = true")
    int getUpInterfaceCount(Long deviceId);
    @Query("SELECT COUNT(d) FROM DeviceInterfaceDao d WHERE d.networkDeviceDao.id = :deviceId AND d.isConnected = false")
    int getDownInterfaceCount(Long deviceId);

    @Query("SELECT COUNT(d) FROM DeviceInterfaceDao d WHERE d.networkDeviceDao.id = :deviceId")
    int getInterfaceCount(Long deviceId);

    List<DeviceInterfaceDao> findByNetworkDeviceDao(NetworkDeviceDao networkDeviceDao);
    @Query("SELECT d FROM DeviceInterfaceDao d WHERE d.networkDeviceDao.id=:deviceId AND d.interfaceName=:interfaceName")
    DeviceInterfaceDao findByInterfaceNameAndDeviceId(String interfaceName,
                                                      Long deviceId);

    @Query("SELECT d FROM DeviceInterfaceDao d WHERE d.networkDeviceDao.id = :deviceId ORDER BY d.interfaceName")
    List<DeviceInterfaceDao> getInterfaceBySortedOrder(Long deviceId);
}
