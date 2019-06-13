import React, {Component} from 'react';


class US605GetCurrentTemperature extends Component {

  constructor(props) {
    super(props);
    this.state = {
      item: [],
      roomID: ''
    }
  }

  componentDidMount() {
    const token = localStorage.getItem('loginToken')
    fetch('https://localhost:8443/roomMonitoring/currentRoomTemperature/' + this.props.roomID, {
        headers: {
          'Authorization': token,
          "Access-Control-Allow-Credentials": true,
          "Access-Control-Allow-Origin": "*",
          "Content-Type": "application/json"
        }
      }
    )
      .then(res => res.json())
      .then((json) => {
        this.setState({
          item: json,
        })
      })
      .catch(console.log)
  }

  render() {
    var {item,roomID, } = this.state;

    return (
      <div>
        <p></p>
        {item.rel == "This room does not exist." ? 'No data available': item.toString() + 'Cº'}
      </div>
    );
  }

}

export default US605GetCurrentTemperature;
