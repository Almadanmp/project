import React from 'react';



const Dashboard = React.lazy(() => import('./views/Dashboard'));


const HouseMonitoring = React.lazy(()=> import('./views/House/HouseMonitoring/HouseMonitoring'))
const RoomMonitoring = React.lazy(()=> import('./views/Room/RoomMonitoring/RoomMonitoring'))
const RoomConfiguration = React.lazy(()=> import('./views/Room/RoomConfiguration/RoomConfiguration'))
const HouseConfiguration = React.lazy(()=> import('./views/House/HouseConfiguration/Example'))

const Users = React.lazy(() => import('./views/Users/Users'));
const User = React.lazy(() => import('./views/Users/User'));
const UnderMaintenance = React.lazy(() => import('./views/UnderMaintenance'));

// https://github.com/ReactTraining/react-router/tree/master/packages/react-router-config
const routes = [
  { path: '/', exact: true, name: 'House' },
  { path: '/dashboard', name: 'Dashboard', component: Dashboard },
  { path: '/house/monitoring', name: 'House Monitoring', component: HouseMonitoring },
  { path: '/room/monitoring', name: 'Room Monitoring', component: RoomMonitoring },
  { path: '/room/configuration', name: 'Room Configuration', component: RoomConfiguration },
  { path: '/house/configuration', name: 'House Configuration', component: HouseConfiguration },
  { path: '/maintenance', name: 'UnderMaintenance', component: UnderMaintenance },
  { path: '/users', exact: true,  name: 'Users', component: Users },
  { path: '/users/:id', exact: true, name: 'User Details', component: User },
];

export default routes;
