import React from 'react';
import 'react-day-picker/lib/style.css';
import US101Button from './US101Button';

class SelectHouseAddressLocation extends React.Component {

  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.state = {
      geographicAreaId: this.props.geographicAreaId,
      street: '',
      number: '',
      zip: '',
      town: '',
      country: '',
      latitude: '',
      longitude: '',
      altitude: ''
    };
  }

  handleInputChange = attribute => event => {
    this.setState({
      [attribute]: event.target.value
    });
  };


  handleSubmit() {
    this.props.onFetchSensor(this.state);
  }


  render() {
    const {street, number, zip, town, country, latitude, longitude, altitude} = this.state;
    return (
      <div>
        <label>Street:
          <input value={street} type="text" name="street" placeholder="Street"
                 onChange={this.handleInputChange('street')}/>
        </label>
        <p></p>
        <label>Number:
          <input value={number} type="number" name="number" placeholder="Number"
                 onChange={this.handleInputChange('number')}/>
        </label>
        <p></p>
        <label>Zip:
          <input value={zip} type="number" name="zip" placeholder="Zip"
                 onChange={this.handleInputChange('zip')}/>
        </label>
        <p></p>
        <label>Town:
          <input value={town} type="text" name="town" placeholder="Town"
                 onChange={this.handleInputChange('town')}/>
        </label>
        <p></p>
        <label>Country:
          <input value={country} type="text" name="country" placeholder="Country"
                 onChange={this.handleInputChange('country')}/>
        </label>
        <p></p>
        <p></p>
        <label>Latitude:
          <input value={latitude} type="number" name="latitude" placeholder="Latitude"
                 onChange={this.handleInputChange('latitude')}/>
        </label>
        <p></p>
        <p></p>
        <label>Longitude:
          <input value={longitude} type="number" name="longitude" placeholder="Longitude"
                 onChange={this.handleInputChange('longitude')}/>
        </label>
        <p></p>
        <p></p>
        <label>Altitude:
          <input value={altitude} type="number" name="altitude" placeholder="Altitude"
                 onChange={this.handleInputChange('altitude')}/>
        </label>
        <p></p>
        <US101Button geographicAreaId={this.props.geographicAreaId} street={this.state.street} number={this.state.number} zip={this.state.zip}
                     town={this.state.town} country={this.state.country} latitude={this.state.latitude}
                     longitude={this.state.longitude} altitude={this.state.altitude}/>
      </div>
    )
  }
}

export default SelectHouseAddressLocation;
