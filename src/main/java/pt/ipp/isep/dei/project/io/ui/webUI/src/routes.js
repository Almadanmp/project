import React from 'react';


const About  = React.lazy(() => import('./views/Pages/About'));
const LogInPage = React.lazy(()=> import('./login/LogInPage'));
const HouseMonitoring = React.lazy(()=> import('./views/House/HouseMonitoring/HouseMonitoring'));
const RoomMonitoring = React.lazy(()=> import('./views/Room/RoomMonitoring/RoomMonitoring'));
const RoomConfiguration = React.lazy(()=> import('./views/Room/RoomConfiguration/RoomConfiguration'));
const HouseConfiguration = React.lazy(()=> import('./views/House/HouseConfiguration/HouseConfiguration'));

const Users = React.lazy(() => import('./views/Users/Users'));
const User = React.lazy(() => import('./views/Users/User'));
const UnderMaintenance = React.lazy(() => import('./views/UnderMaintenance'));

// https://github.com/ReactTraining/react-router/tree/master/packages/react-router-config
const routes = [
  { path: '/', exact: true, name: 'House'},
  { path: '/login', name: 'Login', component: LogInPage },
  { path: '/house/monitoring', name: 'House Monitoring', component: HouseMonitoring },
  { path: '/room/monitoring', name: 'Room Monitoring', component: RoomMonitoring },
  { path: '/room/configuration', name: 'Room Configuration', component: RoomConfiguration },
  { path: '/house/configuration', name: 'House Configuration', component: HouseConfiguration },
  { path: '/maintenance', name: 'UnderMaintenance', component: UnderMaintenance },
  { path: '/users', exact: true,  name: 'Users', component: Users },
  { path: '/users/:id', exact: true, name: 'User Details', component: User },

  //Pages
  { path: '/about', name: 'About', component: About },
];

export default routes;
