import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchGATs} from './Actions002';

class US002Redux extends Component {
  constructor(props) {
    super(props);
  }

  componentDidMount() {
    this.props.onFetchTypes();
  }

  render() {
    const {listGATypes} = this.props;
    return (
      <>
        <h4>
          Existing geographic area types:
          <p></p>
          {listGATypes.map(listGATypes => (
          <h5 key={listGATypes.id}>{listGATypes.name}</h5>
        ))}
        </h4>
      </>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    listGATypes: state.Reducer002.listGATypes
  }
};

const mapDispatchToProps = (dispatch) => {
  return {
    onFetchTypes: () => {
      dispatch(fetchGATs())
    }

  }
};

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(US002Redux);
