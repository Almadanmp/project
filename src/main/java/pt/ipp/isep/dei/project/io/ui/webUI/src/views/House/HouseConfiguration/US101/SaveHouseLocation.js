import React, {Component} from 'react';

class SaveHouseLocation extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken');
    const geographicAreaId = this.props.geographicAreaId;
    const street = this.props.street;
    const number = this.props.number;
    const zip = this.props.zip;
    const town = this.props.town;
    const country = this.props.country;
    const latitude = this.props.latitude;
    const longitude = this.props.longitude;
    const altitude = this.props.altitude

    console.log(this.props);
    fetch('https://localhost:8443/houseSettings/house', {
      method: 'put',
      headers: {
        'Authorization': token,
        "Access-Control-Allow-Credentials": true,
        "Access-Control-Allow-Origin": "*",
        "Content-Type": "application/json"
      },
      body: JSON.stringify({geographicAreaId, street, number, zip, town, country, latitude, longitude, altitude})
    })
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log);
  };

  render() {
    return (
      <div>
        <p>The house location has been altered with the following parameters:</p>
        <ul>
          <li>
            Geographic Area Id: {this.props.geographicAreaId}
          </li>
          <li>
            Street: {this.props.street}
          </li>
          <li>
            Number: {this.props.number}
          </li>
          <li>
            Zip: {this.props.zip}
          </li>
          <li>
            Town: {this.props.town}
          </li>
          <li>
            Country: {this.props.country}
          </li>
          <li>
            Latitude: {this.props.latitude}
          </li>
          <li>
            Longitude: {this.props.longitude}
          </li>
          <li>
            Altitude: {this.props.altitude}
          </li>
        </ul>
      </div>
    );
  }
}

export default SaveHouseLocation;
