import {combineReducers} from 'redux';

import Reducer002 from "./views/GeographicArea/US002/Reducers002";

import Reducers004 from "./views/GeographicArea/US004/Reducers004";
import Reducer108 from './views/House/HouseConfiguration/US108/Reducers108.js';
import Reducers600 from './views/House/HouseMonitoring/USRedux/US600Redux/Reducers600.js';
import Reducers605 from './views/Room/RoomMonitoring/US605/US605Redux/Reducers605.js';
import Reducers620 from './views/House/HouseMonitoring/USRedux/US620Redux/Reducers620.js';
import Reducers630 from './views/House/HouseMonitoring/USRedux/US630Redux/Reducers630.js';
import Reducers631 from './views/House/HouseMonitoring/USRedux/US631Redux/Reducers631.js';
import Reducers623 from './views/House/HouseMonitoring/USRedux/US623Redux/Reducers623.js';
import Reducers147 from './views/EnergyGrid/US147/Reducers147';
import Reducers633 from './views/House/HouseMonitoring/USRedux/US633Redux/Reducers633.js';

import ReducersGetRoomsNotInGrid from './views/EnergyGrid/US147/ReducersGetRoomsNotInGrid'

export default combineReducers({

  Reducer002,

  Reducers004,
  Reducer108,
  Reducers600,
  Reducers147,
  Reducers605,
  Reducers620,
  Reducers623,
  Reducers630,
  Reducers631,
  Reducers633,

  ReducersGetRoomsNotInGrid,
})
