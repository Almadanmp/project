import axios from 'axios';

export const FETCH_ENERGYGRID_INFO_STARTED = 'FETCH_ENERGYGRID_INFO_STARTED';

export const fetchEnergyGrid = ({name, maxContractedPower}) => {
  return dispatch => {
    dispatch(fetchEnergyGridInfo(name, maxContractedPower));
    const houseID = "01";
    const data = {name, houseID, maxContractedPower};
    axios
      .post('http://localhost:9898/gridSettings/grids', data,
        {
          headers: {'Content-Type': 'application/json'},
          body: {name, houseID, maxContractedPower}
        })
  };
};

export function fetchEnergyGridInfo(name, maxContractedPower) {
  return {
    type: FETCH_ENERGYGRID_INFO_STARTED,
    payload: {
      name: name,
      houseID: "01",
      maxContractedPower: maxContractedPower
    }
  }
}
