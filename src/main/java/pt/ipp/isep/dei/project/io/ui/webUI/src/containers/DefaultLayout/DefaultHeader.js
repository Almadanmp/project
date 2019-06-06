import React, { Component } from 'react';
import { Link, NavLink } from 'react-router-dom';
import { Badge, Nav, NavItem } from 'reactstrap';
import PropTypes from 'prop-types';

import { AppAsideToggler, AppNavbarBrand, AppSidebarToggler } from '@coreui/react';
import logo from '../../assets/img/brand/logo.svg'
import sygnet from '../../assets/img/brand/sygnet.svg'

const propTypes = {
  children: PropTypes.node,
};

const defaultProps = {};

class DefaultHeader extends Component {
  render() {

    // eslint-disable-next-line
    const { children, ...attributes } = this.props;

    return (
      <React.Fragment>
        <AppSidebarToggler className="d-lg-none" display="inline-block" mobile />
        <AppSidebarToggler className="d-md-down-none"  />
        <AppNavbarBrand
          full={{ src: logo, width: 100, height: 100, alt: 'SmartHome Logo' }}
          minimized={{ src: sygnet, width: 60, height: 60, alt: 'SmartHome Logo' }}
        />
        <img src="https://imgur.com/ppSoEr1.png" align="right" width={100} height={20}/>
        <Nav align-items="left" className="d-md-down-none" navbar>
        </Nav>


      </React.Fragment>

    );
  }
}

DefaultHeader.propTypes = propTypes;
DefaultHeader.defaultProps = defaultProps;

export default DefaultHeader;
